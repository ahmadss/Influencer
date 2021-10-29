package com.example.test.service;

import com.example.test.request.RequestKecamatan;
import com.example.test.request.RequestKelurahan;
import com.example.test.request.RequestKodepost;
import com.example.test.request.RequestKota;
import com.example.test.request.RequestLogin;
import com.example.test.request.RequestProvinsi;
import com.example.test.result.ResultKecamatan;
import com.example.test.result.ResultKelurahan;
import com.example.test.result.ResultKodepost;
import com.example.test.result.ResultKota;
import com.example.test.result.ResultLogin;
import com.example.test.result.ResultProvinsi;
import com.example.test.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIInterface {

    @POST("login-customer")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Call<ResultLogin> doLogin(@Body RequestLogin data);

    @POST("get-provinsi")
    @Headers({ "Content-Type: application/json;charset=UTF-8",
            "Authorization: Bearer MEE4M3JPb2RpTExPdFVXRWtwVm5uVkl6S2lsUTF6MlFKRXVjQ3cvYnJxaExhS1hBam5mK2ZUMHNKVDQ1bFZBRzQ4N3k4YW51VEF3VFdLaUE5VG1vaWtJMFBUazdsZUc5djJsRGwrN3NQckZHNlI0VWxVS2VTeG1kcXVXYzVQcUZJOWgwaW81Vm5tcjN0MkJUellPbmRkZFpvcXdFODgvajhjM3BHNzZ4QWRidTVXb0NZUHM5blRvTEFFSDhqVnlYQ1RKb3pYRzhST3JhV251dW96M0Vzd2NtbVVDbkFvQjlCRy9KRGxCLzhPR3ZPT0svdjA0SzJyOCtKWXVvSzdRekRQN1pvM1gvODNpTndZM2VZOXkvdHZhRnJHS25pOHdOZExSVy9WNCswZUlWMlVBV1cwVlF3V0dzVS9oOHpSbjY=",
            "Key: dGRMTnhuOEMxRDJNZXRUMXBseCtSRm14WjNrellkQVowZFVFSUFmVGwrM1BadmFjUHkwcndkTjRnN3gxdTN4LzdTTElMOHpSS3JoZ0dPK0xQN3dCOEU1ZWRETkN3WElqMVppTmtKNTcyWTQ9",
            "Timestamp: 2020-01-10 18:02:00",
            "Signature: e24b2448cbf13681e24c1a7b02fa8b8c257898862b45b8d731f8dcaefa0f7f61"
    })
    Call<ResultProvinsi> getProvinsi(@Body RequestProvinsi data);

    @POST("get-kota")
    @Headers({ "Content-Type: application/json;charset=UTF-8",
            "Authorization: Bearer MEE4M3JPb2RpTExPdFVXRWtwVm5uVkl6S2lsUTF6MlFKRXVjQ3cvYnJxaExhS1hBam5mK2ZUMHNKVDQ1bFZBRzQ4N3k4YW51VEF3VFdLaUE5VG1vaWtJMFBUazdsZUc5djJsRGwrN3NQckZHNlI0VWxVS2VTeG1kcXVXYzVQcUZJOWgwaW81Vm5tcjN0MkJUellPbmRkZFpvcXdFODgvajhjM3BHNzZ4QWRidTVXb0NZUHM5blRvTEFFSDhqVnlYQ1RKb3pYRzhST3JhV251dW96M0Vzd2NtbVVDbkFvQjlCRy9KRGxCLzhPR3ZPT0svdjA0SzJyOCtKWXVvSzdRekRQN1pvM1gvODNpTndZM2VZOXkvdHZhRnJHS25pOHdOZExSVy9WNCswZUlWMlVBV1cwVlF3V0dzVS9oOHpSbjY=",
            "Key: dGRMTnhuOEMxRDJNZXRUMXBseCtSRm14WjNrellkQVowZFVFSUFmVGwrM1BadmFjUHkwcndkTjRnN3gxdTN4LzdTTElMOHpSS3JoZ0dPK0xQN3dCOEU1ZWRETkN3WElqMVppTmtKNTcyWTQ9",
            "Timestamp: 2020-01-10 18:02:00",
            "Signature: e24b2448cbf13681e24c1a7b02fa8b8c257898862b45b8d731f8dcaefa0f7f61"
    })
    Call<ResultKota> getKota(@Body RequestKota data);

    @POST("get-kecamatan")
    @Headers({ "Content-Type: application/json;charset=UTF-8",
            "Authorization: Bearer MEE4M3JPb2RpTExPdFVXRWtwVm5uVkl6S2lsUTF6MlFKRXVjQ3cvYnJxaExhS1hBam5mK2ZUMHNKVDQ1bFZBRzQ4N3k4YW51VEF3VFdLaUE5VG1vaWtJMFBUazdsZUc5djJsRGwrN3NQckZHNlI0VWxVS2VTeG1kcXVXYzVQcUZJOWgwaW81Vm5tcjN0MkJUellPbmRkZFpvcXdFODgvajhjM3BHNzZ4QWRidTVXb0NZUHM5blRvTEFFSDhqVnlYQ1RKb3pYRzhST3JhV251dW96M0Vzd2NtbVVDbkFvQjlCRy9KRGxCLzhPR3ZPT0svdjA0SzJyOCtKWXVvSzdRekRQN1pvM1gvODNpTndZM2VZOXkvdHZhRnJHS25pOHdOZExSVy9WNCswZUlWMlVBV1cwVlF3V0dzVS9oOHpSbjY=",
            "Key: dGRMTnhuOEMxRDJNZXRUMXBseCtSRm14WjNrellkQVowZFVFSUFmVGwrM1BadmFjUHkwcndkTjRnN3gxdTN4LzdTTElMOHpSS3JoZ0dPK0xQN3dCOEU1ZWRETkN3WElqMVppTmtKNTcyWTQ9",
            "Timestamp: 2020-01-10 18:02:00",
            "Signature: e24b2448cbf13681e24c1a7b02fa8b8c257898862b45b8d731f8dcaefa0f7f61"
    })
    Call<ResultKecamatan> getKecamatan(@Body RequestKecamatan data);


    @POST("get-kelurahan")
    @Headers({ "Content-Type: application/json;charset=UTF-8",
            "Authorization: Bearer MEE4M3JPb2RpTExPdFVXRWtwVm5uVkl6S2lsUTF6MlFKRXVjQ3cvYnJxaExhS1hBam5mK2ZUMHNKVDQ1bFZBRzQ4N3k4YW51VEF3VFdLaUE5VG1vaWtJMFBUazdsZUc5djJsRGwrN3NQckZHNlI0VWxVS2VTeG1kcXVXYzVQcUZJOWgwaW81Vm5tcjN0MkJUellPbmRkZFpvcXdFODgvajhjM3BHNzZ4QWRidTVXb0NZUHM5blRvTEFFSDhqVnlYQ1RKb3pYRzhST3JhV251dW96M0Vzd2NtbVVDbkFvQjlCRy9KRGxCLzhPR3ZPT0svdjA0SzJyOCtKWXVvSzdRekRQN1pvM1gvODNpTndZM2VZOXkvdHZhRnJHS25pOHdOZExSVy9WNCswZUlWMlVBV1cwVlF3V0dzVS9oOHpSbjY=",
            "Key: dGRMTnhuOEMxRDJNZXRUMXBseCtSRm14WjNrellkQVowZFVFSUFmVGwrM1BadmFjUHkwcndkTjRnN3gxdTN4LzdTTElMOHpSS3JoZ0dPK0xQN3dCOEU1ZWRETkN3WElqMVppTmtKNTcyWTQ9",
            "Timestamp: 2020-01-10 18:02:00",
            "Signature: e24b2448cbf13681e24c1a7b02fa8b8c257898862b45b8d731f8dcaefa0f7f61"
    })
    Call<ResultKelurahan> getKelurahan(@Body RequestKelurahan data);

    @POST("get-kodepos")
    @Headers({ "Content-Type: application/json;charset=UTF-8",
            "Authorization: Bearer MEE4M3JPb2RpTExPdFVXRWtwVm5uVkl6S2lsUTF6MlFKRXVjQ3cvYnJxaExhS1hBam5mK2ZUMHNKVDQ1bFZBRzQ4N3k4YW51VEF3VFdLaUE5VG1vaWtJMFBUazdsZUc5djJsRGwrN3NQckZHNlI0VWxVS2VTeG1kcXVXYzVQcUZJOWgwaW81Vm5tcjN0MkJUellPbmRkZFpvcXdFODgvajhjM3BHNzZ4QWRidTVXb0NZUHM5blRvTEFFSDhqVnlYQ1RKb3pYRzhST3JhV251dW96M0Vzd2NtbVVDbkFvQjlCRy9KRGxCLzhPR3ZPT0svdjA0SzJyOCtKWXVvSzdRekRQN1pvM1gvODNpTndZM2VZOXkvdHZhRnJHS25pOHdOZExSVy9WNCswZUlWMlVBV1cwVlF3V0dzVS9oOHpSbjY=",
            "Key: dGRMTnhuOEMxRDJNZXRUMXBseCtSRm14WjNrellkQVowZFVFSUFmVGwrM1BadmFjUHkwcndkTjRnN3gxdTN4LzdTTElMOHpSS3JoZ0dPK0xQN3dCOEU1ZWRETkN3WElqMVppTmtKNTcyWTQ9",
            "Timestamp: 2020-01-10 18:02:00",
            "Signature: e24b2448cbf13681e24c1a7b02fa8b8c257898862b45b8d731f8dcaefa0f7f61"
    })
    Call<ResultKodepost> getKodePos(@Body RequestKodepost data);
}
