package com.hossain.zakaria.caloriesappwithretrofit.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.hossain.zakaria.caloriesappwithretrofit.R;
import com.hossain.zakaria.caloriesappwithretrofit.activities.DetailActivity;
import com.hossain.zakaria.caloriesappwithretrofit.adapters.RecyclerViewAdapter;
import com.hossain.zakaria.caloriesappwithretrofit.models.Calorie;
import com.hossain.zakaria.caloriesappwithretrofit.retrofits.RetrofitApiClient;
import com.hossain.zakaria.caloriesappwithretrofit.interfaces.ApiInterface;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FruitsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener , RecyclerViewAdapter.OnItemMyClickListener {

    public static final String NAME_KEY = "name";
    public static final String CALORIE_KEY = "calorie";
    public static final String IMAGE_PATH_KEY = "image_path";

    private RecyclerView fruitsRecyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private SwipeRefreshLayout swipeRefreshLayoutForFruits;
    private TextView noDataFoundFruits;

    private List<Calorie> calorieListForFruits;
    private Context context;

    public FruitsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fruits, container, false);
        context = container.getContext();

        noDataFoundFruits = view.findViewById(R.id.noDataFoundForFruits);
        fruitsRecyclerView = view.findViewById(R.id.fruitsRecyclerView);
        swipeRefreshLayoutForFruits = view.findViewById(R.id.swipeRefreshForFruits);

        swipeRefreshLayoutForFruits.setOnRefreshListener(this);
        swipeRefreshLayoutForFruits.setColorSchemeResources(R.color.colorPrimary);

        fetchInformationFromServer();

        return view;
    }

    private void fetchInformationFromServer() {
        swipeRefreshLayoutForFruits.setRefreshing(true);

        ApiInterface apiInterface = RetrofitApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Calorie>> call = apiInterface.getCaloriesInfo("fruits");

        call.enqueue(new Callback<List<Calorie>>() {
            @Override
            public void onResponse(@NonNull Call<List<Calorie>> call, @NonNull Response<List<Calorie>> response) {

                calorieListForFruits = response.body();

                //200 = server response success
                if (response.code() == 200 && calorieListForFruits != null) {
                    noDataFoundFruits.setVisibility(View.GONE);
                    Collections.reverse(calorieListForFruits);

                    recyclerViewAdapter = new RecyclerViewAdapter(calorieListForFruits, context);
                    fruitsRecyclerView.setLayoutManager(new LinearLayoutManager(context));
                    fruitsRecyclerView.setHasFixedSize(true);
                    fruitsRecyclerView.setAdapter(recyclerViewAdapter);

                    swipeRefreshLayoutForFruits.setRefreshing(false);
                    recyclerViewAdapter.setOnItemMyClickListener(FruitsFragment.this);

                } else {
                    if (calorieListForFruits == null || calorieListForFruits.isEmpty()) {
                        noDataFoundFruits.setVisibility(View.VISIBLE);
                    }

                    swipeRefreshLayoutForFruits.setRefreshing(false);
                    Toast.makeText(context, "Error: " + response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Calorie>> call, @NonNull Throwable t) {
                if (calorieListForFruits == null || calorieListForFruits.isEmpty()) {
                    noDataFoundFruits.setVisibility(View.VISIBLE);
                }

                swipeRefreshLayoutForFruits.setRefreshing(false);
                Toast.makeText(context, "Error: " + t.getMessage() + " Or check your internet connection", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onRefresh() {
        fetchInformationFromServer();
    }

    @Override
    public void onItemMyClick(int position, View view) {
        Intent intent = new Intent(context, DetailActivity.class);

        //for shared transition //with xml set android:transitionName="my_transition" into imageview
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(Objects.requireNonNull(getActivity()), view, Objects.requireNonNull(ViewCompat.getTransitionName(view)));

        Calorie calorie = calorieListForFruits.get(position);
        intent.putExtra(NAME_KEY, calorie.getName());
        intent.putExtra(CALORIE_KEY, String.valueOf(calorie.getCalorie()));
        intent.putExtra(IMAGE_PATH_KEY, calorie.getImagePath());

        startActivity(intent, optionsCompat.toBundle());
    }
}
