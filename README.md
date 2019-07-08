
### 多个InfoWindow显示框架
这个框架可以在地图上的marker上显示多个infoWindow(高德默认只能同时显示一个infoWindow)

效果图如下:
![image](https://raw.githubusercontent.com/jikun2008/YisingleMarkerView/master/pic/moreInfoWindow.png)


#### 使用

##### !!!!! 请在使用前 将高德地图的sdk加到工程中

```java

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}


	dependencies {
	        implementation 'com.github.jikun2008:YisingleMarkerView:v1.0.3'
	}
	
```


```java 
private LatLng startlatLng = new LatLng(30.65769, 104.062388);

        startPointMarkerView = new PointMarkerView.Builder(getApplicationContext(), getAmap())
                .setPosition(startlatLng)
                .setIcon(BitmapDescriptorFactory.fromResource(R.mipmap.amap_start))
                .create();

        startPointMarkerView.bindInfoWindowView(new BaseMarkerView.BaseInfoWindowView<String>(R.layout.info_window, "start") {
            @Override
            public void bindData(MapInfoWindowViewHolder viewHolder, String data) {
                viewHolder.setText(R.id.tvInfoWindow, data);
            }
        });
        startPointMarkerView.addToMap();
        startPointMarkerView.showInfoWindow("开始");

```

