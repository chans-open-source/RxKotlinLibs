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
  Kt.App.init(this.applicationContext)
}
```
./Other.kt
```
// Get application context instance.
Kt.App.getContext(): Context

// Get application resources instance.
Kt.App.getResources(): Resources

// Get last activity instance of activity stack.
Kt.App.last(): Activity

// Exit application.
Kt.App.exit()
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
Kt.Request
  .create<ProjectModel>(view as BaseActivity)
  .api(Kt.Request.api(Test::class.java).projectList(1, 2))
  .obs(Obs(view as BaseActivity))
```
Response Callback
```
class Obs(activity: BaseActivity) : Kt.Observer<ProjectModel>(activity) {
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
Kt.Broadcast.register(): Boolean

// Unregister
Kt.Broadcast.unregister(): Boolean

// Send Broadcast
Kt.Broadcast.send()

// Send Broadcast Synchronized
Kt.Broadcast.sendSync()
```

### HandlerHelper
```
// Create
Kt.Handler.create(): Handler?

// Send Empty Message
Kt.Handler.what(): Boolean

// Send Message Instance.
Kt.Handler.send(): Boolean

// Send Config
Kt.Handler.sendMessage(): Boolean

// Remove Message
Kt.Handler.remove()

// Obtain Message
Kt.Handler.obtainMessage(): Message
```

### PermissionHelper
```
// Check
Kt.Permission.check(): Boolean

// Request Permission
Kt.Permission.request()
```

### SharedPreferencesHelper
```
// Set
Kt.SharedPreferences.set(key, value)

// Get
Kt.SharedPreferences.get(key, <T>, default): T?
```

### UIHelper
```
// Show toast layout.
Kt.UI.showToast()

// Show loading dialog.
Kt.UI.showLoading()

// Hide loading dialog.
Kt.UI.hideLoading()

// Bind click listener.
Kt.UI.bindClick()

// Bind long click listener.
Kt.UI.bindLongClick()

// Quick navigation to another activity.
Kt.UI.quickTo()

// Navigation to another activity for result.
Kt.UI.quickToForResult()

// Find view by id of layout.
Kt.UI.get()
```

### ImageLoader
```
// Load image.
Kt.Image.load()

// Load image of no cache.
Kt.Image.noCacheLoad()
```