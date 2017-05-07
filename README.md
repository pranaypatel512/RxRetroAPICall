## RxRetroAPICall
API call example using Retrofit and RxJava2

### About RxRetroAPICall Example

* Common API calling strcture to avoide code duplication. 
* Easy to use and understand.
* Using Latest version of Retrofit and Rxjava2

#### RxAPICallback.java
```
/**
 * Common Callback to call API request response.
 * @param <P> : response Type
 */
public interface RxAPICallback<P> {
    void onSuccess(P t);

    void onFailed(Throwable throwable);
}
```
#### RxAPICallHelper.java
```
/**
 *
 * Common API Call Helper to make API call.
 */
public class RxAPICallHelper {
    public RxAPICallHelper() {
    }

    public static <T> Disposable call(Observable<T> observable, final RxAPICallback<T> rxAPICallback) {
        if (observable == null) {
            throw new IllegalArgumentException("Observable must not be null.");
        }


        if (rxAPICallback == null) {
            throw new IllegalArgumentException("Callback must not be null.");
        }

        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<T>() {
                    @Override
                    public void accept(@NonNull T t) throws Exception {
                        rxAPICallback.onSuccess(t);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        if (throwable != null) {
                            rxAPICallback.onFailed(throwable);
                        } else {
                            rxAPICallback.onFailed(new Exception("Error: Something went wrong in api call."));
                        }
                    }
                });

    }
}
```

#### Example Call

```
/**
     * Call API to get stackoverflow User badge details
     */
    private void getUserBadgeDetails() {
        mProgressDialog.show();
        StackOverFlowUserBadgesService badgesService = ApiProduction.getInstance(this).provideService(StackOverFlowUserBadgesService.class);
        Observable<StackOverFlowUserBadgesResponse> responseObservable = badgesService.getBadges("2949612");
        disposable = RxAPICallHelper.call(responseObservable, new RxAPICallback<StackOverFlowUserBadgesResponse>() {
            @Override
            public void onSuccess(StackOverFlowUserBadgesResponse badgesResponse) {
                mProgressDialog.dismiss();
                showToast(badgesResponse.getItems().size() > 0 ? "Success" : "Failed");
                if (badgesResponse.getItems().size() > 0) {
                    calculateBadges(badgesResponse);
                }
                disposeCall();
            }

            @Override
            public void onFailed(Throwable throwable) {
                disposeCall();
                mProgressDialog.dismiss();
                showToast(throwable.getLocalizedMessage());
            }
        });
    }
```


### Example Output

<img src="https://github.com/pranaypatel512/RxRetroAPICall/blob/master/app/screenshot/RxJava_Retrofit_API_call_output.png" width="400">



### Find this project useful ? ❤️

* Support it by clicking the ⭐️ button on the upper right of this page. ✌️


### License
```
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
