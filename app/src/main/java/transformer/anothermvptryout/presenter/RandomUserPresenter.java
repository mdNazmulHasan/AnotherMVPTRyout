package transformer.anothermvptryout.presenter;

import android.util.Log;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import transformer.anothermvptryout.model.RandomUser;
import transformer.anothermvptryout.model.RandomUserApi;
import transformer.anothermvptryout.view.RandomUserView;

import static io.reactivex.internal.operators.observable.ObservableBlockingSubscribe.subscribe;

/**
 * Created by Nazmul on 28-Jul-17.
 */

public class RandomUserPresenter extends MvpBasePresenter<RandomUserView> {
    RandomUserApi randomUserApi;
    RandomUser randomUser;

    public void getData(final boolean pullToRefresh) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.randomuser.me/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        randomUserApi = retrofit.create(RandomUserApi.class);

        Observable<RandomUser> observableResponse = randomUserApi.getAllData();
        observableResponse.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(randomUser -> {
                    if (randomUser != null) {
                        if (isViewAttached()) {
                            getView().setData(randomUser);
                            getView().showContent();
                        }
                    }
                });
        /*Call<RandomUser> randomUserCall=randomUserApi.getAllData();
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

        });*/
    }
}
