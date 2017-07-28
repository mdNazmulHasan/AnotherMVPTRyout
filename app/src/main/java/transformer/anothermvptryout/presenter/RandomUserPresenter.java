package transformer.anothermvptryout.presenter;

import android.util.Log;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import transformer.anothermvptryout.model.RandomUser;
import transformer.anothermvptryout.model.RandomUserApi;
import transformer.anothermvptryout.view.RandomUserView;

/**
 * Created by Nazmul on 28-Jul-17.
 */

public class RandomUserPresenter extends MvpBasePresenter<RandomUserView> {
    RandomUserApi randomUserApi;

    public void getData(final boolean pullToRefresh) {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://api.randomuser.me/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        randomUserApi=retrofit.create(RandomUserApi.class);
        Call<RandomUser> randomUserCall=randomUserApi.getAllData();
        randomUserCall.enqueue(new Callback<RandomUser>() {
            @Override
            public void onResponse(Call<RandomUser> call, Response<RandomUser> response) {
                RandomUser randomUser=response.body();
                Log.e("response", "onResponse: "+randomUser.getResults().get(0).getDob());
                if (isViewAttached()){
                    getView().setData(randomUser);
                    getView().showContent();
                }
            }

            @Override
            public void onFailure(Call<RandomUser> call, Throwable t) {

            }

        });
    }
}
