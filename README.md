## RxKotlinLibs

>Integrated RxJava, RxAndroid, Kotlin, Some Application manager utils.

>[Demo](https://github.com/ChangedenCZD/RxAndroid4Kotlin)

### Import
Module/build.gradle
```
dependencies {
  ...
  implementation 'com.chansos.libs:rxkotlin:0.0.4'
  ...
}
```

### Application manager utils
[AppManager](#appmanager)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
[BaseActivity](#baseactivity)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
[BaseFragment](#basefragment)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
[BaseViewPagerFragment](#baseviewpagerfragment)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
[BaseRecyclerViewAdapter](#baserecyclerviewadapter)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
[MVP-Struct](#mvp-struct)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
[Http-Request](#http-request)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
[BroadcastHelper](#broadcasthelper)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
[HandlerHelper](#handlerhelper)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
[PermissionHelper](#permissionhelper)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
[SharedPreferencesHelper](#sharedpreferenceshelper)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
[UIHelper](#uihelper)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
[ImageLoader](#imageloader)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;


### AppManager
./Application.kt
```
override fun onCreate() {
  super.onCreate()
  AppManager.init(this.applicationContext)
}
```
./Other.kt
```
// Get application context instance.
AppManager.getContext(): Context

// Get application resources instance.
AppManager.getResources(): Resources

// Get last activity instance of activity stack.
AppManager.last(): Activity

// Exit application.
AppManager.exit()
```

### BaseActivity
```
@PageLayoutId(R.layout.activity_first)
@PageOptions(title = "FirstActivity")
class FirstActivity : BaseActivity() {}
```

### BaseFragment
```
@PageLayoutId(R.layout.fragment_first)
class FirstFragment : BaseFragment() {}
```

### BaseViewPagerFragment
```
@PageLayoutId(R.layout.fragment_first)
class FirstFragment : BaseViewPagerFragment(){
  override fun onInitialize() {
  // Initialize instance.
  }

  override fun onFirstTime() {
  // On this page first time resume.
  }

  override fun onSecondTime() {
  // On this page second time resume.
  }
}
```

### BaseRecyclerViewAdapter
Extends
```
class ImageListAdapter : BaseRecyclerViewAdapter<String>() {
  override fun getRootLayoutResId(): Int {
  // Return content view layout resources id.
    return R.layout.item_image
  }

  override fun onViewCreate(view: View) {
  // Set content view attributes.
  }

  override fun onBind(viewHolder: BaseRecyclerViewHolder, data: String, position: Int) {
  // Set content view informations.
    viewHolder.setImage(R.id.image_view, data)
  }
}
```
Instance
```
// implements BaseRecyclerViewAdapter.OnItemClickListener,BaseRecyclerViewAdapter.OnItemLongClickListener
val adapter = ImageListAdapter()
adapter.onItemClickListener = this
adapter.onItemLongClickListener = this
adapter.setDataList(imageList)
```

### MVP Struct
Contract
```
interface Contract : BaseContract {
  interface View : BaseContract.BaseView{}

  interface Presenter : BaseContract.BasePresenter {}
}
```
View
```
@PageLayoutId(R.layout.fragment_first)
@ModulePresenter("com.chansos.rxandroid.kotlin.module.first.Presenter")
class FirstFragment : BaseViewPagerFragment(), Contract.View {
  private lateinit var presenter: Presenter
}
```
Presenter
```
class Presenter : Contract.Presenter {
  private lateinit var view: Contract.View
}
```

### Http Request
>Integrated RxJava, RxAndroid, OkHttp, FastJSON

Api Config
```
@Domain("https://www.chansos.com/api/")
@BaseUrl("article/")
interface Test {
  @GET("project")
  fun projectList(@Query("page") page: Int, @Query("size") size: Int, @Query("query") query: String?=""): Observable<ProjectModel>
}
```
Response Model
```
class ProjectModel() : Parcelable {
  ...
}
```
Request
```
RxRequest
  .create<ProjectModel>(view as BaseActivity)
  .api(RxRequest.api(Test::class.java).projectList(1, 2))
  .obs(Obs(view as BaseActivity))
```
Response Callback
```
class Obs(activity: BaseActivity) : RxRequest.RxObserver<ProjectModel>(activity) {
  override fun onNext(t: ProjectModel) {
    LogUtils.d(JSON.toJSONString(t))
  }

  override fun onError(e: Throwable) {
    super.onError(e)
    LogUtils.e(e)
  }
}
```

### BroadcastHelper
```
// Register
AppHelper.Broadcast.register(): Boolean

// Unregister
AppHelper.Broadcast.unregister(): Boolean

// Send Broadcast
AppHelper.Broadcast.send()

// Send Broadcast Synchronized
AppHelper.Broadcast.sendSync()
```

### HandlerHelper
```
// Create
AppHelper.Handler.create(): Handler?

// Send Empty Message
AppHelper.Handler.what(): Boolean

// Send Message Instance.
AppHelper.Handler.send(): Boolean

// Send Config
AppHelper.Handler.sendMessage(): Boolean

// Remove Message
AppHelper.Handler.remove()

// Obtain Message
AppHelper.Handler.obtainMessage(): Message
```

### PermissionHelper
```
// Check
AppHelper.Permission.check(): Boolean

// Request Permission
AppHelper.Permission.request()
```

### SharedPreferencesHelper
```
// Set
AppHelper.SharedPreferences.set(key, value)

// Get
AppHelper.SharedPreferences.get(key, <T>, default): T?
```

### UIHelper
```
// Show toast layout.
AppHelper.UI.showToast()

// Show loading dialog.
AppHelper.UI.showLoading()

// Hide loading dialog.
AppHelper.UI.hideLoading()

// Bind click listener.
AppHelper.UI.bindClick()

// Bind long click listener.
AppHelper.UI.bindLongClick()

// Quick navigation to another activity.
AppHelper.UI.quickTo()

// Navigation to another activity for result.
AppHelper.UI.quickToForResult()

// Find view by id of layout.
AppHelper.UI.get()
```

### ImageLoader
```
// Load image.
AppHelper.Image.load()

// Load image of no cache.
AppHelper.Image.noCacheLoad()
```