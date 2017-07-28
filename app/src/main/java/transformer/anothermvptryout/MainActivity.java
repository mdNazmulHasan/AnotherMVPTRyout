package transformer.anothermvptryout;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import transformer.anothermvptryout.model.RandomUser;
import transformer.anothermvptryout.model.RandomUserApi;
import transformer.anothermvptryout.presenter.RandomUserPresenter;
import transformer.anothermvptryout.view.RandomUserView;

public class MainActivity extends MvpActivity<RandomUserView,RandomUserPresenter> implements RandomUserView{
    RandomUserApi randomUserApi;

    @BindView(R.id.sampleText)
    TextView SampleTextView;    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        loadData(false);
    }

    @NonNull
    @Override
    public RandomUserPresenter createPresenter() {
        return new RandomUserPresenter();
    }


    @Override
    public void showLoading(boolean pullToRefresh) {
    }

    @Override
    public void showContent() {

    }

    @Override
    public void showError(Throwable e, boolean pullToRefresh) {

    }

    @Override
    public void setData(RandomUser data) {
        SampleTextView.setText(data.getInfo().getVersion());
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.getData(pullToRefresh);
    }
}
