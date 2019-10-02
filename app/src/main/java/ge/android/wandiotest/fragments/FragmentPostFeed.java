package ge.android.wandiotest.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ge.android.wandiotest.R;
import ge.android.wandiotest.adapters.AdapterPostFeed;
import ge.android.wandiotest.interfaces.OnFragmentInteractiionlistener;
import ge.android.wandiotest.interfaces.WandioAPI;
import ge.android.wandiotest.utils.Post;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentPostFeed extends Fragment {

    private List<Post> mListOfPosts;
    private OnFragmentInteractiionlistener mListener;

    public FragmentPostFeed() {

    }

    public static FragmentPostFeed newInstance() {

        return new FragmentPostFeed();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListOfPosts = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WandioAPI.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WandioAPI wandioAPI = retrofit.create(WandioAPI.class);

        Call<Post> apiCall = wandioAPI.getPosts();
        apiCall.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(@NonNull Call<Post> call, @NonNull Response<Post> response) {
                assert response.body() != null;
                mListOfPosts = response.body().getPosts();
            }

            @Override
            public void onFailure(@NonNull Call<Post> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        if (view instanceof RecyclerView) {
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setAdapter(new AdapterPostFeed(mListOfPosts, mListener, getContext()));
        }
        return view;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractiionlistener) {
            mListener = (OnFragmentInteractiionlistener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
