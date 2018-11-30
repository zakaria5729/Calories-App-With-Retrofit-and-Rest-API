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

import static com.hossain.zakaria.caloriesappwithretrofit.fragments.FruitsFragment.CALORIE_KEY;
import static com.hossain.zakaria.caloriesappwithretrofit.fragments.FruitsFragment.IMAGE_PATH_KEY;
import static com.hossain.zakaria.caloriesappwithretrofit.fragments.FruitsFragment.NAME_KEY;

public class VegetablesFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, RecyclerViewAdapter.OnItemMyClickListener {

    private RecyclerView vegetablesRecyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private SwipeRefreshLayout swipeRefreshLayoutForVegetables;
    private TextView noDataFoundVegetables;

    private List<Calorie> calorieListForVegetables;
    private Context context;

    public VegetablesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vegetables, container, false);
        context = container.getContext();

        noDataFoundVegetables = view.findViewById(R.id.noDataFoundForVegetables);
        vegetablesRecyclerView = view.findViewById(R.id.vegetablesRecyclerView);
        swipeRefreshLayoutForVegetables = view.findViewById(R.id.swipeRefreshForVegetables);

        swipeRefreshLayoutForVegetables.setOnRefreshListener(this);
        swipeRefreshLayoutForVegetables.setColorSchemeResources(R.color.colorPrimary);

        fetchInformationFromServer();

        return view;
    }

    private void fetchInformationFromServer() {
        swipeRefreshLayoutForVegetables.setRefreshing(true);

        ApiInterface apiInterface = RetrofitApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Calorie>> call = apiInterface.getCaloriesInfo("vegetables");

        call.enqueue(new Callback<List<Calorie>>() {
            @Override
            public void onResponse(@NonNull Call<List<Calorie>> call, @NonNull Response<List<Calorie>> response) {

                calorieListForVegetables = response.body();

                //200 = server response success
                if (response.code() == 200 && calorieListForVegetables != null) {
                    noDataFoundVegetables.setVisibility(View.GONE);
                    Collections.reverse(calorieListForVegetables);

                    recyclerViewAdapter = new RecyclerViewAdapter(calorieListForVegetables, context);
                    vegetablesRecyclerView.setLayoutManager(new LinearLayoutManager(context));
                    vegetablesRecyclerView.setHasFixedSize(true);
                    vegetablesRecyclerView.setAdapter(recyclerViewAdapter);

                    swipeRefreshLayoutForVegetables.setRefreshing(false);
                    recyclerViewAdapter.setOnItemMyClickListener(VegetablesFragment.this);

                } else {
                    if (calorieListForVegetables == null || calorieListForVegetables.isEmpty()) {
                        noDataFoundVegetables.setVisibility(View.VISIBLE);
                    }

                    swipeRefreshLayoutForVegetables.setRefreshing(false);
                    Toast.makeText(context, "Error: " + response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Calorie>> call, @NonNull Throwable t) {
                if (calorieListForVegetables == null || calorieListForVegetables.isEmpty()) {
                    noDataFoundVegetables.setVisibility(View.VISIBLE);
                }

                swipeRefreshLayoutForVegetables.setRefreshing(false);
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

        Calorie calorie = calorieListForVegetables.get(position);
        intent.putExtra(NAME_KEY, calorie.getName());
        intent.putExtra(CALORIE_KEY, String.valueOf(calorie.getCalorie()));
        intent.putExtra(IMAGE_PATH_KEY, calorie.getImagePath());

        startActivity(intent, optionsCompat.toBundle());
    }
}
