<style>
@import "./DrawingManager_min.css";
</style>
<template>  
  <div style="width:100%;height:900px;">
      <div style="height:5%;width:100%;">
          <Button @click="save" style="" type="primary">保存</Button>
            <Button @click="goback" style="" type="primary">返回</Button>
            <Button @click="clearall" style="" type="primary">清除</Button>
      </div>
      <div id="markmap" style="height:94%;-webkit-transition: all 0.5s ease-in-out;transition: all 0.5s ease-in-out;"></div>
  
  </div>
</template>  
<script>
var markmap = null;
var drawingManager = null;
var overlays = [];
var rightclkFunc = function(e){
    var drawMode = drawingManager.getDrawingMode();
    if(drawMode==BMAP_DRAWING_POLYGON||drawMode==BMAP_DRAWING_RECTANGLE)
        return;
    var rightMenu = new BMap.ContextMenu();
    rightMenu.addItem(new BMap.MenuItem('返回',function(){
        clearAll();
        markmap.removeContextMenu(rightMenu);
        backPage();
    },{width:140,id:'addarearect'}));
    rightMenu.addItem(new BMap.MenuItem('添加矩形区域',function(){
        addAreaRect(e.point);
        markmap.removeContextMenu(rightMenu);
    },{width:140,id:'addarearect'}));
    rightMenu.addItem(new BMap.MenuItem('添加不规则区域',function(){
        addAreaPolygon(e.point);
        markmap.removeContextMenu(rightMenu);
    },{width:140,id:'addareapolygon'}));
    rightMenu.addItem(new BMap.MenuItem('清除新加区域',function(){
        clearLastArea();
        markmap.removeContextMenu(rightMenu);
    },{width:140,id:'cleararea'}));
    rightMenu.addItem(new BMap.MenuItem('附近投放区',function(){
        loadNearbyPutinArea(e.point);
        markmap.removeContextMenu(rightMenu);
    },{width:140,id:'putin'}));
    rightMenu.addItem(new BMap.MenuItem('附近禁投区',function(){
        loadNearbyProhibitPutinArea(e.point);
        markmap.removeContextMenu(rightMenu);
    },{width:140,id:'exputin'}));
    markmap.addContextMenu(rightMenu);
};
function addAreaRect(point){
    drawingManager.setDrawingMode(BMAP_DRAWING_RECTANGLE);
    drawingManager.open();
    markmap.centerAndZoom(point,20);
}

function addAreaPolygon(point){
    drawingManager.setDrawingMode(BMAP_DRAWING_POLYGON);
    drawingManager.open();
    markmap.centerAndZoom(point,20);
}

/**
 * 清除新加区域
 */
function clearLastArea(){
    var ov = overlays.pop();
    markmap.removeOverlay(ov);
}

/**
 * 加载在当前地图视野范围内并在当前位置附近1.5km的投放区
 * @param {*} point --当前位置
 */
function loadNearbyPutinArea(point){

}

/**
 * 加载在当前地图视野范围内并在当前位置附近1.5km的禁投区
 * @param {*} point --当前位置
 */
function loadNearbyProhibitPutinArea(point){

}

/**
 * 返回调用页
 */
function backPage(){
    window.history.go(-1);
}

function clearAll() {
    if(markmap==null)
        return;
    for(var i = 0; i < overlays.length; i++){
        markmap.removeOverlay(overlays[i]);
    }
    overlays.length = 0;
}

function draw(data){
    var styleOptions = {
      strokeColor: "red", //边线颜色。
      fillColor: "red", //填充颜色。当参数为空时，圆形将没有填充效果。
      strokeWeight: 1, //边线的宽度，以像素为单位。
      strokeOpacity: 0.8, //边线透明度，取值范围0 - 1。
      fillOpacity: 0.6, //填充的透明度，取值范围0 - 1。
      strokeStyle: "solid" //边线的样式，solid或dashed。
    };
    var polygon=new BMap.Polygon(data.areacoords,styleOptions);
    overlays.push(polygon);
    markmap.addOverlay(polygon);
}

var  temp=null;
export default {
    props:{
        id:'',
        areacoords:''
    },
    methods:{
        save:function(){
            var coords = localStorage.getItem("coords");
            clearAll();
            this.$router.push('/richangjianguan/area');
        },
        goback:function(){
            clearAll();
            this.$router.go(-1);
        },
        clearall:function(){
            clearAll();
        }
    },
  mounted: function() {    
    markmap = new BMap.Map("markmap"); // 创建Map实例
    markmap.centerAndZoom(new BMap.Point(104.072051, 30.662979), 13); // 初始化地图,设置中心点坐标和地图级别
    markmap.enableScrollWheelZoom(true); // 开启鼠标滚轮缩放
    markmap.setMapStyle({ style: "grassgreen" });

    var styleOptions = {
      strokeColor: "red", //边线颜色。
      fillColor: "red", //填充颜色。当参数为空时，圆形将没有填充效果。
      strokeWeight: 1, //边线的宽度，以像素为单位。
      strokeOpacity: 0.8, //边线透明度，取值范围0 - 1。
      fillOpacity: 0.6, //填充的透明度，取值范围0 - 1。
      strokeStyle: "solid" //边线的样式，solid或dashed。
    };

    //实例化鼠标绘制工具
    drawingManager = new BMapLib.DrawingManager(markmap, {
      isOpen: false, //是否开启绘制模式
      enableDrawingTool: true, //是否显示工具栏
      enableCalculate: false,
      drawingType: BMAP_DRAWING_RECTANGLE,
      drawingToolOptions: {
        anchor: BMAP_ANCHOR_TOP_RIGHT, //位置
        offset: new BMap.Size(5, 5), //偏离值
        scale: 0.6,
        drawingModes: [BMAP_DRAWING_POLYGON, BMAP_DRAWING_RECTANGLE]
      },
      circleOptions: styleOptions, //圆的样式
      polylineOptions: styleOptions, //线的样式
      polygonOptions: styleOptions, //多边形的样式
      rectangleOptions: styleOptions //矩形的样式*/
    });
    //添加鼠标绘制工具监听事件，用于获取绘制结果
    drawingManager.addEventListener("overlaycomplete", function(e) {
        clearAll();
      overlays.push(e.overlay);
      drawingManager.close();
      drawingManager.setDrawingMode(0);
    });

    drawingManager.addEventListener("polygoncomplete", function(e) {
      var coordlist = e.getPath();
      var strCoords = '{"areacoords":[';
      for (var i = 0; i < coordlist.length; i++) {
        var _lat = coordlist[i].lat;
        var _lng = coordlist[i].lng;
        strCoords += '{"lng":' + _lng + ',"lat":' + _lat + "}";
        if (i < coordlist.length - 1) strCoords += ",";
      }
      strCoords += "]}";
      localStorage.setItem("coords", strCoords);
    });
    drawingManager.addEventListener("rectanglecomplete", function(e) {
      var coordlist = e.getPath();
      var strCoords = '{"areacoords":[';
      for (var i = 0; i < coordlist.length; i++) {
        var _lat = coordlist[i].lat;
        var _lng = coordlist[i].lng;
        strCoords += '{"lng":' + _lng + ',"lat":' + _lat + "}";
        if (i < coordlist.length - 1) strCoords += ",";
      }
      strCoords += "]}";
      localStorage.setItem("coords", strCoords);
    });

    markmap.addEventListener("click", function(e) {
      if (e.overlay) return;
      var drawMode = drawingManager.getDrawingMode();
      if (
        drawMode == BMAP_DRAWING_POLYGON ||
        drawMode == BMAP_DRAWING_RECTANGLE
      )
        return;
      markmap.centerAndZoom(e.point, 20);
    });
    markmap.addEventListener("rightclick", rightclkFunc);
  }
};
</script>
<style scoped>
#markmap{height:100%}
</style>
