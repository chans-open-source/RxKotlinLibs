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
[AppManager](#appmanager)&nbsp;&nbsp;&nbsp;&nbsp;
[BaseActivity](#baseactivity)&nbsp;&nbsp;&nbsp;&nbsp;
[BaseFragment](#basefragment)&nbsp;&nbsp;&nbsp;&nbsp;
[BaseViewPagerFragment](#baseviewpagerfragment)&nbsp;&nbsp;&nbsp;&nbsp;
[BaseRecyclerViewAdapter](#baserecyclerviewadapter)&nbsp;&nbsp;&nbsp;&nbsp;
[MVP Struct](#mvp-struct)&nbsp;&nbsp;&nbsp;&nbsp;
[Http Request](#http-request)&nbsp;&nbsp;&nbsp;&nbsp;
[BroadcastHelper](#broadcasthelper)&nbsp;&nbsp;&nbsp;&nbsp;
[HandlerHelper](#handlerhelper)&nbsp;&nbsp;&nbsp;&nbsp;
[PermissionHelper](#permissionhelper)&nbsp;&nbsp;&nbsp;&nbsp;
[SharedPreferencesHelper](#sharedpreferenceshelper)&nbsp;&nbsp;&nbsp;&nbsp;
[UIHelper](#uihelper)&nbsp;&nbsp;&nbsp;&nbsp;
[ImageLoader](#imageloader)&nbsp;&nbsp;&nbsp;&nbsp;


### AppManager    [Top](#application-manager-utils)
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

### BaseActivity    [Top](#application-manager-utils)
```
@LayoutResId(R.layout.activity_first)
@PageDefaultOptions(title = "FirstActivity")
class FirstActivity : BaseActivity() {}
```

### BaseFragment    [Top](#application-manager-utils)
```
@LayoutResId(R.layout.fragment_first)
class FirstFragment : BaseFragment() {}
```

### BaseViewPagerFragment
```
@LayoutResId(R.layout.fragment_first)
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
@LayoutResId(R.layout.fragment_first)
@AutowirePresent("com.chansos.rxandroid.kotlin.module.first.Presenter")
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
RxKotlin
  .create<ProjectModel>(view as BaseActivity)
  .api(RxKotlin.api(Test::class.java).projectList(1, 2))
  .obs(Obs(view as BaseActivity))
```
Response Callback
```
class Obs(activity: BaseActivity) : RxKotlin.RxObserver<ProjectModel>(activity) {
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
BroadcastHelper.register(): Boolean

// Unregister
BroadcastHelper.unregister(): Boolean

// Send Broadcast
BroadcastHelper.send()

// Send Broadcast Synchronized
BroadcastHelper.sendSync()
```

### HandlerHelper
```
// Create
HandlerHelper.create(): Handler?

// Send Empty Message
HandlerHelper.what(): Boolean

// Send Message Instance.
HandlerHelper.send(): Boolean

// Send Config
HandlerHelper.sendMessage(): Boolean

// Remove Message
HandlerHelper.remove()

// Obtain Message
HandlerHelper.obtainMessage(): Message
```

### PermissionHelper
```
// Check
PermissionHelper.check(): Boolean

// Request Permission
PermissionHelper.request()
```

### SharedPreferencesHelper
```
// Set
SharedPreferencesHelper.set(key, value)

// Get
SharedPreferencesHelper.get(key, <T>, default): T?
```

### UIHelper
```
// Show toast layout.
UIHelper.showToast()

// Show loading dialog.
UIHelper.showLoading()

// Hide loading dialog.
UIHelper.hideLoading()

// Bind click listener.
UIHelper.bindClick()

// Bind long click listener.
UIHelper.bindLongClick()

// Quick navigation to another activity.
UIHelper.quickTo()

// Navigation to another activity for result.
UIHelper.quickToForResult()

// Find view by id of layout.
UIHelper.get()
```

### ImageLoader
```
// Load image.
ImageLoader.load()

// Load image of no cache.
ImageLoader.noCacheLoad()
```