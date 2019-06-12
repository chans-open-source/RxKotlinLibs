## RxKotlinLibs

>Integrated RxJava, RxAndroid, Kotlin, Some Application manager utils.
1
>[Demo](https://github.com/ChangedenCZD/RxAndroid4Kotlin)

### Import
>Import libs. 

Module/build.gradle
```
dependencies {
  ...
  implementation 'com.chansos.libs:rxkotlin:0.2.6'
  ...
}
```

### Application manager utils
>[AppManager](#appmanager) : Manager all activity and application.

>[BaseActivity](#baseactivity) : Extend BaseActivity and implement its methods to let you focus on developing your Activity.

>[BaseFragment](#basefragment) : Extend BaseFragment and implement its methods to let you focus on developing your Fragment.

>[BaseViewPagerFragment](#baseviewpagerfragment) : Extend BaseFragment and implement its methods to let you focus on developing your ViewPager.

>[BaseRecyclerViewAdapter](#baserecyclerviewadapter) : Quickly create an Adapter instance of RecyclerView.

>[MVP-Struct](#mvp-struct) : Implement MVP programing.

>[Http-Request](#http-request) : Make a quick Http request.

>[BroadcastHelper](#broadcasthelper) : Easily manage your broadcasts.

>[HandlerHelper](#handlerhelper) : Easily manage your handlers.

>[PermissionHelper](#permissionhelper) : Easily manage your permissions.

>[SharedPreferencesHelper](#sharedpreferenceshelper) : Easily manage your shared preferences.

>[UIHelper](#uihelper) : Quickly manipulate your UI.

>[ImageLoader](#imageloader) : Easily load image into ImageView.


### AppManager
>Manager all activity and application.

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
>Extend BaseActivity and implement its methods to let you focus on developing your Activity.

```
@PageLayoutId(R.layout.activity_first)
@PageOptions(title = "FirstActivity")
class FirstActivity : BaseActivity() {}
```

### BaseFragment
>Extend BaseFragment and implement its methods to let you focus on developing your Fragment.

```
@PageLayoutId(R.layout.fragment_first)
class FirstFragment : BaseFragment() {}
```

### BaseViewPagerFragment
>Extend BaseFragment and implement its methods to let you focus on developing your ViewPager.

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
>Quickly create an Adapter instance of RecyclerView.

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
>Implement MVP programing.

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
>Make a quick Http request.
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
>Easily manage your broadcasts.

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
>Easily manage your handlers.

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
>Easily manage your permissions.

```
// Check
Kt.Permission.check(): Boolean

// Request Permission
Kt.Permission.request()
```

### SharedPreferencesHelper
>Easily manage your shared preferences.

```
// Set
Kt.SharedPreferences.set(key, value)

// Get
Kt.SharedPreferences.get(key, <T>, default): T?
```

### UIHelper
>Quickly manipulate your UI.

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
>Easy to load image into ImageView.

```
// Load image.
Kt.Image.load()

// Load image of no cache.
Kt.Image.noCacheLoad()
```