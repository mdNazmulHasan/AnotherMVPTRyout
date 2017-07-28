package transformer.anothermvptryout.model;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Nazmul on 28-Jul-17.
 */

public interface RandomUserApi {
    @GET("/")
    Call<RandomUser> getAllData();
}
