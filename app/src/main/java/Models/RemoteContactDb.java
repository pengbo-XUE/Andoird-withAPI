package Models;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * DbContext interface, used for retrofit
 *
 */
public interface RemoteContactDb {
    @POST("Contacts")
    Call<Contact> ContactCreate(@Body Contact contact);
}
