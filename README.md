![Download](https://api.bintray.com/packages/chenenyu/maven/FreeTimeLine/images/download.svg)  
## [Chinese Version 中文版](README-zh-rCN.md)
# FreeTimeLine
*Beautiful and powerful time-line component for android.* 

[Demo apk](demo.apk)

![gif](arts/ftl-en.gif)

### Android Studio:  
`compile 'com.chenenyu.freetimeline:ftl:2.1'`
### Eclipse:  
Hehe...  

## Avaliable attrs:

|attr|format|description|
|:---:|:---:|:---:|
|top_type|enum|Top type(sucker/solid/hollow)|
|node_type|enum|Node type(solid/hollow)|
|bottom_type|enum|Bottom type(solid/hollow)|
|line_color|color|Vertival axes color|
|solid_color|color|Solid circle color|
|hollow_color|color|Hollow circle color|
|sucker_color|color|Sucker type color|
|toggle_color|color|Toggle button color|
|left_color|color|Left text color|
|left_size|dimension|Left text size(default 13sp)|
|parent_color|color|Title text color|
|parent_size|dimension|Title text size(default 14sp)|
|child_color|color|Detail text color|
|child_size|dimension|Detail text size(default 12sp)|
|show_toggle|boolean|Whether to show toggle button|

All of above can be configed in xml or code.


***Other features and custom attrs will be added one after another!***

## How to use?
in xml:  

	<com.chenenyu.freetimeline.FreeTimeLine  
		android:id="@+id/ftl"  
		android:layout_width="match_parent"  
		android:layout_height="wrap_content"  
        ftl:bottom_type="solid"  
        ftl:child_color="#60615d"  
        ftl:node_type="hollow"  
        ftl:parent_color="#5b4d0e"  
        ftl:solid_color="#c65e4a"  
        ftl:show_toggle="true"  
        ftl:sucker_color="#FF4EAAB2"  
        ftl:toggle_color="#4e85b2"  
        ftl:top_type="sucker"/>  

Then set elements:  

`ftl.setElements(List<FreeTimeLineElement>);`  

You also can change defalut config in code:

`ftl.setConfig(FreeTimeLineConfig);`


**Welcome to submit pull requests and open iusses!  : )**

## License
 
Please make sure you know about this [license](http://www.apache.org/licenses/LICENSE-2.0)!  

```
Copyright 2016 chenenyu.

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