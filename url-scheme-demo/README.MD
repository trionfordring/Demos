# URL-SCHEME-DEMO

在手机浏览器中访问特定的链接可以打开指定app。  
这个特殊的链接即为`URL-SCHEME`。

## URL-SCHEME-LIST

### 系统

- 设置 (App调用可添加“App-”前缀
- 电池电量 Prefs:root=BATTERY_USAGE
- 存储空间 Prefs:root=General&path=STORAGE_ICLOUD_USAGE/DEVICE_STORAGE
- 蜂窝数据 Prefs:root=MOBILE_DATA_SETTINGS_ID
- Wi-Fi 设置 Prefs:root=WIFI
- 蓝牙设置 Prefs:root=Bluetooth
- 定位设置 Prefs:root=Privacy&path=LOCATION
- 辅助功能 Prefs:root=General&path=ACCESSIBILITY
- 关于手机 Prefs:root=General&path=About
- 键盘设置 Prefs:root=General&path=Keyboard
- 显示设置 Prefs:root=DISPLAY
- 声音设置 Prefs:root=Sounds
- App Store 设置 Prefs:root=STORE
- 墙纸设置 Prefs:root=Wallpaper
- 捷径 workflow://run-workflow?name=[name]&input=[input]
- 世界时钟 Clock-worldclock://
- 闹钟 Clock-alarm://
- 秒表 Clock-stopwatch://
- 倒计时 Clock-timer://
- 打开相册 Photos://
- 查找iPhone fmip1://
- 打电话 tel://110
- 备忘录 mobilenotes://
- 日历 calshow://

### 工具

- Chrome googlechrome://
- 手机百度 BaiduSSO://
- QQ浏览器 mqqbrowser://
- uc浏览器 ucbrowser://
- 搜狗输入法 com.sogou.sogouinput://
- 圈子账本 qzzb://
- 我的密码 findmykey://
- 我查查 wcc://
- 有道词典 yddict://
- 名片全能王 camcard://
- Evernote evernote://
- 迅雷 thunder://
- QQ同步助手 qqpim://
- 天气通 sinaweather://
- 墨迹天气 rm434209233MojiWeather://
- 同花顺 amihexin://
- 腾讯企业邮箱 qqbizmailDistribute2://
- 腾讯手机管家 mqqsecure://
- 网易邮箱 neteasemail://
- 百度云 baiduyun://

### 社交

- 知乎 zhihu://
  - （某篇文章）zhihu://articles/27758684  
  - （某个专栏）zhihu://columns/lishuai

- QQ mqq://
- 微信
  - (扫一扫)weixin://scanqrcode  

- 微博
  - 微博国际版 weibointernational:
  - (热搜)weibointernational://hotsearch

- 百度贴吧 com.baidu.tieba://
- 腾讯微博 TencentWeibo://
- 陌陌 momochat://
- 天涯社区 tianya://

### 购物

- 淘宝 taobao://
    - (搜索)taobao://s.taobao.com/?q=裙子

- 支付宝 alipay://
    - (付款)alipayqr://platformapi/startapp?saId=20000056
    - (扫一扫)alipayqr://platformapi/startapp?saId=10000007

- 京东 openapp.jdmoble://
- 美团 imeituan://
- 大众点评 dianping://
- 1号店 yhd://
- 唯品会 vipshop://

### 出行

- 高德地图
  - (公交导航)iosamap://path?sourceApplication=日历&sid=BGVIS1&sname=$我的位置&did=BGVIS2&dname=目的地&dlat=纬度&dlon=经度&dev=0&m=0&t=0  
// t为出行方式：0 驾车，1 公交，2 步行 
// m为出行要求 当t为驾车时：0速度最快，1费用最少，2距离最短，3不走高速，4躲避拥堵，5不走高速且避免收费，6不走高速且躲避拥堵，7躲避收费和拥堵，8不走高速躲避收费和拥堵 
当t为公交时：0最快捷，2最少换乘，3最少步行，5不乘地铁 ，7只坐地铁 ，8时间短  

- 百度地图 baidumap://
  - (导航)baidumap://map/direction?origin=我的位置&destination=公司&mode=driving
//mode 导航模式，固定为transit、driving、walking
- 腾讯地图 qqmap://
  (导航）qqmap://map/routeplan?from=我的位置&type=drive&tocoord=36.547901,104.258354&to=dest&coord_type=1&policy=0
- 北京交警 zcblbjjj://
- 滴滴出行 diditaxi://
- 摩拜单车扫码 mobike://scanqr
- 订票助手 trainassist://
- 艺龙旅行 elongIPhone://
- 携程无线 CtripWireless://
- 淘宝旅行 taobaotravel://

### 娱乐

- 优酷 youku://
- 爱奇艺视频 qiyi-iphone://
- PPTV pptv://
- 今日头条 snssdk141://
- 哔哩哔哩动画 bilibili://
- 优酷 youku://
- 搜狐视频 sohuvideo://
- 腾讯视频 tenvideo://
- 掌阅iReader iReader://
- 网易新闻 newsapp://
- 腾讯新闻 qqnews://
- 网易云音乐 orpheus://
  - (识别音乐)orpheus://recognize
  - （本地音乐）orpheus://download
  - （私人FM）orpheus://radio
  - （我的喜欢）orpheus://playlist/32778108  !歌单编号可以通过分享歌单的链接获得2018.10.31更新
- QQ音乐 qqmusic://
- 豆瓣FM doubanradio://
- 百度音乐 baidumusic://
- 百度视频 baiduvideoiphone://

### 其他

- 招商银行 cmbmobilebank://
- 建设银行 wx2654d9155d70a468://
- 工商银行 com.icbc.iphoneclient://
- 浦发银行 wx1cb534bb13ba3dbd://
