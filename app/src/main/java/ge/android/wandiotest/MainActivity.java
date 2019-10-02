package ge.android.wandiotest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

import ge.android.wandiotest.fragments.FragmentPostFeed;
import ge.android.wandiotest.interfaces.OnFragmentInteractiionlistener;
import ge.android.wandiotest.interfaces.WandioAPI;
import ge.android.wandiotest.utils.Post;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements OnFragmentInteractiionlistener {

    private Fragment myFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayFragment(FragmentPostFeed.newInstance(), false);

    }


    public void displayFragment(Fragment fragment, boolean stack) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();

        if(myFragment != null) {
            ft.detach(myFragment);
        }

        ft.replace(R.id.fragmentContainer, fragment);

        if(stack){
            ft.addToBackStack(null);
        } else {
            try {
                fragmentManager.popBackStackImmediate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        ft.commit();

        myFragment = fragment;
    }

    @Override
    public void onFragmentPostFeedInteraction() {

    }
}
