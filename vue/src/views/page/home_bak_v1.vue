<template>  
  <div>  
    <Row>
        <div style="width:100%;height:900px;">
      <div id="markmap" style="height:100%;-webkit-transition: all 0.5s ease-in-out;transition: all 0.5s ease-in-out;"></div>
  
  </div> 
   </Row>
    
  </div>  
</template>  

<script>
// import HomeMap from "./components/home_map.vue";
// import HomeBikeDate from "./components/home_bike_date.vue";
// import echarts from "echarts";
// export default {
//   components: {
//     HomeMap,
//     HomeBikeDate
//   },
//   data() {
//     return {
//       bike_company: [
//         { company: "摩拜", num: "33654" },
//         { company: "ofo", num: "42555" },
//         { company: "其它", num: "23654" }
//       ]
//     };
//   },
//   methods: {
//     create_bike_company: function(data) {
//       let myChart = echarts.init(document.getElementById("bike_company"));
//       let series = {
//         areaStyle: { normal: {} },
//         type: "pie",
//         data: []
//       };
//       for (let i in data) {
//         let temp = { name: data[i].company, value: data[i].num };
//         series.data.push(temp);
//       }
//       let option = {
//         title: { text: "共享单车品牌数量分布" },
//         tooltip: {},
//         legend: {
//           type: "scroll",
//           bottom: 10
//         },
//         series: [series]
//       };
//       myChart.setOption(option);
//     }
//   },
//   mounted: function() {
//     this.create_bike_company(this.$data.bike_company);
//   }
// };
var d = null;
var sessionData = -1;
var centerPoint = "";
var markmap = null;
var drawingManager = null;
var points = "";
var overlays = [];
var dataById = [];
var rightclkFunc = function(e) {
  var drawMode = drawingManager.getDrawingMode();
  if (drawMode == BMAP_DRAWING_POLYGON || drawMode == BMAP_DRAWING_RECTANGLE)
    return;
  var rightMenu = new BMap.ContextMenu();
  rightMenu.addItem(
    new BMap.MenuItem(
      "返回",
      function() {
        clearAll();
        markmap.removeContextMenu(rightMenu);
        backPage();
      },
      { width: 140, id: "addarearect" }
    )
  );
  rightMenu.addItem(
    new BMap.MenuItem(
      "添加矩形区域",
      function() {
        addAreaRect(e.point);
        markmap.removeContextMenu(rightMenu);
      },
      { width: 140, id: "addarearect" }
    )
  );
  rightMenu.addItem(
    new BMap.MenuItem(
      "添加不规则区域",
      function() {
        addAreaPolygon(e.point);
        markmap.removeContextMenu(rightMenu);
      },
      { width: 140, id: "addareapolygon" }
    )
  );
  rightMenu.addItem(
    new BMap.MenuItem(
      "清除新加区域",
      function() {
        clearLastArea();
        markmap.removeContextMenu(rightMenu);
      },
      { width: 140, id: "cleararea" }
    )
  );
  rightMenu.addItem(
    new BMap.MenuItem(
      "附近投放区",
      function() {
        loadNearbyPutinArea(e.point);
        markmap.removeContextMenu(rightMenu);
      },
      { width: 140, id: "putin" }
    )
  );
  rightMenu.addItem(
    new BMap.MenuItem(
      "附近禁投区",
      function() {
        loadNearbyProhibitPutinArea(e.point);
        markmap.removeContextMenu(rightMenu);
      },
      { width: 140, id: "exputin" }
    )
  );
  markmap.addContextMenu(rightMenu);
};

function openWindows(params) {
  if (sessionData == -1) {
    alert("当前数据还未加载完毕，请稍等片刻！");
    return;
  }
  var point = centerPoint;
  d
    .$ajax({
      method: "post",
      url: d.$uri + "mapInterface/getTongjiByCoordinate",
      data: params
    })
    .then(function(res) {
      var str = "该地区共享单车总数为：" + res.data.total;
      var sContent =
        "<div style='width:45%;float:left;margin-left:20px;font-family: Arial,Helvetica,'Microsoft YaHei',sans-serif;'>" +
        "</br><b>该地区共享单车总数为：</b>" +
        res.data.total +
        "</br>" +
        "</div>";
      var opts = {
        width: 450, // 信息窗口宽度
        height: 100 // 信息窗口高度
      };
      for (let i = 0; i < res.data.rows.length; i++) {
        let element = res.data.rows[i];
        sContent +=
          "<div style='width:45%;float:left;margin-left:20px;font-family: Arial,Helvetica,'Microsoft YaHei',sans-serif;'>" +
          "</br><b>" +
          element.companyName +
          "：</b>" +
          element.total +
          "</br>" +
          "</div>";
        opts.height += 30;
      }
      var InfoWindow = new BMap.InfoWindow(sContent, opts);
      markmap.openInfoWindow(InfoWindow, new BMap.Point(point[0], point[1]));
    })
    .catch(function(err) {
      console.log(err);
    });
}
function addAreaRect(point) {
  drawingManager.setDrawingMode(BMAP_DRAWING_RECTANGLE);
  drawingManager.open();
  markmap.centerAndZoom(point, 20);
}

function addAreaPolygon(point) {
  drawingManager.setDrawingMode(BMAP_DRAWING_POLYGON);
  drawingManager.open();
  markmap.centerAndZoom(point, 20);
}

/**
 * 清除新加区域
 */
function clearLastArea() {
  var ov = overlays.pop();
  markmap.removeOverlay(ov);
}

/**
 * 加载在当前地图视野范围内并在当前位置附近1.5km的投放区
 * @param {*} point --当前位置
 */
function loadNearbyPutinArea(point) {}

/**
 * 加载在当前地图视野范围内并在当前位置附近1.5km的禁投区
 * @param {*} point --当前位置
 */
function loadNearbyProhibitPutinArea(point) {}

/**
 * 返回调用页
 */
function backPage() {
  window.history.go(-1);
}

function clearAll() {
  if (markmap == null) return;
  for (var i = 0; i < overlays.length; i++) {
    markmap.removeOverlay(overlays[i]);
  }
  overlays.length = 0;
  points = "";
  dataPolygonById = [];
}

function getArrayAreacCoordinate(model) {
  var quyu = model.areacCoordinate.split("&");
  var points = [];
  for (let k = 0; k < quyu.length; k++) {
    let element = quyu[k].split(";");
    element.map(function(e) {
      if (typeof points[k] != "object") points[k] = [];
      points[k].push([
        parseFloat(e.split(",")[0]),
        parseFloat(e.split(",")[1])
      ]);
    });
  }
  return points;
}

//判断坐标是否在一个区域内
//checkPoint-[104.080469, 30.619083]
//polygonPoints-[[104.07966, 30.620932], [104.07984, 30.619783], [104.081852, 30.619767], [104.081762, 30.620995]]
function isInPolygon(checkPoint, polygonPoints) {
  var counter = 0;
  var i;
  var xinters;
  var p1, p2;
  var pointCount = polygonPoints.length;
  p1 = polygonPoints[0];
  for (i = 1; i <= pointCount; i++) {
    p2 = polygonPoints[i % pointCount];
    if (
      checkPoint[0] > Math.min(p1[0], p2[0]) &&
      checkPoint[0] <= Math.max(p1[0], p2[0])
    ) {
      if (checkPoint[1] <= Math.max(p1[1], p2[1])) {
        if (p1[0] != p2[0]) {
          xinters =
            (checkPoint[0] - p1[0]) * (p2[1] - p1[1]) / (p2[0] - p1[0]) + p1[1];
          if (p1[1] == p2[1] || checkPoint[1] <= xinters) {
            counter++;
          }
        }
      }
    }
    p1 = p2;
  }
  if (counter % 2 == 0) {
    return false;
  } else {
    return true;
  }
}

function drowMap(id) {
  if (id != "6941139967a311e8a93e1843420641b8") id = "";
  let params = new URLSearchParams();
  params.append("parentId", id);
  d
    .$ajax({
      method: "post",
      url: d.$uri + "mapInterface/getAreaDataInfo",
      data: params
    })
    .then(function(res) {
      dataById = [];
      var rows = res.data.rows;
      if (rows.length > 0) clearAll();
      for (let i = 0; i < rows.length; i++) {
        let element = rows[i];
        var areacCoordinate = element.areacCoordinate;
        dataById.push({ id: element.id, areacCoordinate: areacCoordinate });
        draw(areacCoordinate, element.id);
      }

      // draw({ areacoords: areacCoordinate });
    })
    .catch(function(err) {
      console.log(err);
    });
}
var dataPolygonById = [];
var q = null;
function draw(data, id) {
  var ds = data.split("&");
  for (let i = 0; i < ds.length; i++) {
    let element = ds[i];
    var styleOptions = {
      // strokeColor: "red", //边线颜色。
      // strokeWeight: 1, //边线的宽度，以像素为单位。
      // strokeOpacity: 0.8, //边线透明度，取值范围0 - 1。
      // fillOpacity: 0, //填充的透明度，取值范围0 - 1。
      // strokeStyle: "solid" //边线的样式，solid或dashed。
      strokeWeight: 2,
      strokeColor: "#ff0000"
    };
    var polygon = new BMap.Polygon(element, styleOptions);
    dataPolygonById.push({ id: id, model: polygon });
    polygon.addEventListener("click", function(type) {
      if (sessionData == -1) {
        alert("当前数据还未加载完毕，请稍等片刻！");
        return;
      }
      var id = "";
      var model = null;
      if (markmap.getZoom() > 12) {
        for (let j = 0; j < dataPolygonById.length; j++) {
          let e = dataPolygonById[j];
          if (type.currentTarget === e.model) {
            id = e.id;
            model = e.model;
          }
        }
        if (id != "") {
          let params = new URLSearchParams();
          params.append("id", id);
          var point = centerPoint;
          d
            .$ajax({
              method: "post",
              url: d.$uri + "mapInterface/getTongji",
              data: params
            })
            .then(function(res) {
              var str = "该地区共享单车总数为：" + res.data.total;
              var sContent =
                "<div style='width:45%;float:left;margin-left:20px;font-family: Arial,Helvetica,'Microsoft YaHei',sans-serif;'>" +
                "</br><b>该地区共享单车总数为：</b>" +
                res.data.total +
                "</br>" +
                "</div>";
              var opts = {
                width: 450, // 信息窗口宽度
                height: 100 // 信息窗口高度
              };
              for (let i = 0; i < res.data.rows.length; i++) {
                let element = res.data.rows[i];
                sContent +=
                  "<div style='width:45%;float:left;margin-left:20px;font-family: Arial,Helvetica,'Microsoft YaHei',sans-serif;'>" +
                  "</br><b>" +
                  element.companyName +
                  "：</b>" +
                  element.total +
                  "</br>" +
                  "</div>";
                opts.height += 30;
              }
              var InfoWindow = new BMap.InfoWindow(sContent, opts);
              markmap.openInfoWindow(
                InfoWindow,
                new BMap.Point(point[0], point[1])
              );
            })
            .catch(function(err) {
              console.log(err);
            });
        }
      }
    });

    overlays.push(polygon);
    markmap.addOverlay(polygon);
    q = polygon;
  }
}

var temp = null;
export default {
  props: {
    id: "",
    areacoords: ""
  },
  methods: {
    save: function() {
      var d = this;
      var coords = points;
      var id = this.$route.query.id;
      let params = new URLSearchParams();
      params.append("areaCoordinate", coords);
      params.append("id", id);
      this.$ajax({
        method: "post",
        url: this.$uri + "mapInterface/updateAreaCoordinate",
        data: params
      })
        .then(function(res) {
          clearAll();
          d.$router.push("/richangjianguan/area");
        })
        .catch(function(err) {
          console.log(err);
          clearAll();
          d.$router.push("/richangjianguan/area");
        });
    },
    goback: function() {
      clearAll();
      this.$router.go(-1);
    },
    clearall: function() {
      clearAll();
    }
  },
  mounted: function() {
    d = this;
    markmap = new BMap.Map("markmap"); // 创建Map实例
    markmap.centerAndZoom(new BMap.Point(104.072051, 30.662979), 12); // 初始化地图,设置中心点坐标和地图级别
    markmap.enableScrollWheelZoom(true); // 开启鼠标滚轮缩放
    markmap.setMapStyle({ style: "grassgreen" });

    var styleOptions = {
      strokeWeight: 2,
      strokeColor: "#ff0000"
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
      overlays.push(e.overlay);
      drawingManager.close();
      drawingManager.setDrawingMode(0);
    });

    drawingManager.addEventListener("polygoncomplete", function(e) {
      var coordlist = e.getPath();
      var strCoords = "";
      if (points) {
        for (var i = 0; i < coordlist.length; i++) {
          var _lat = coordlist[i].lat;
          var _lng = coordlist[i].lng;
          strCoords += _lng + "," + _lat;
          if (i < coordlist.length - 1) strCoords += ";";
        }
        points = points + "&" + strCoords;
        //localStorage.setItem("coords", strCoords);
      } else {
        for (var i = 0; i < coordlist.length; i++) {
          var _lat = coordlist[i].lat;
          var _lng = coordlist[i].lng;
          strCoords += _lng + "," + _lat;
          if (i < coordlist.length - 1) strCoords += ";";
        }
        points = strCoords;
        //localStorage.setItem("coords", strCoords);
      }
      let params = new URLSearchParams();
      params.append("coordinate", points);
      openWindows(params);
    });
    drawingManager.addEventListener("rectanglecomplete", function(e) {
      var coordlist = e.getPath();
      var strCoords = "";

      if (points) {
        for (var i = 0; i < coordlist.length; i++) {
          var _lat = coordlist[i].lat;
          var _lng = coordlist[i].lng;
          strCoords += _lng + "," + _lat;
          if (i < coordlist.length - 1) strCoords += ";";
        }
        points = points + "&" + strCoords;
      } else {
        for (var i = 0; i < coordlist.length; i++) {
          var _lat = coordlist[i].lat;
          var _lng = coordlist[i].lng;
          strCoords += _lng + "," + _lat;
          if (i < coordlist.length - 1) strCoords += ";";
        }
        points = strCoords;
        //localStorage.setItem("coords", strCoords);
      }
      let params = new URLSearchParams();
      params.append("coordinate", points);
      openWindows(params);
    });

    markmap.addEventListener("click", function(e) {
      // if (e.overlay) return;
      // var drawMode = drawingManager.getDrawingMode();
      // if (
      //   drawMode == BMAP_DRAWING_POLYGON ||
      //   drawMode == BMAP_DRAWING_RECTANGLE
      // )
      //   return;
      // prompt("鼠标单击地方的经纬度为：", e.point.lng + "," + e.point.lat);
      var point = [e.point.lng, e.point.lat];
      var id = "";
      var zoom = markmap.getZoom();
      f1: for (let i = 0; i < dataById.length; i++) {
        let element = dataById[i];
        var points = getArrayAreacCoordinate(element);
        f2: for (let i = 0; i < points.length; i++) {
          var b = isInPolygon(point, points[i]);
          if (b) {
            id = element.id;
            break f1;
          }
        }
      }
      if (id != "") {
        if (zoom > 12) {
        } else {
          let params = new URLSearchParams();
          params.append("parentId", id);
          d
            .$ajax({
              method: "post",
              url: d.$uri + "mapInterface/getAreaDataInfo",
              data: params
            })
            .then(function(res) {
              var rows = res.data.rows;
              if (rows.length > 0) {
                clearAll();
              }
              for (let i = 0; i < rows.length; i++) {
                let element = rows[i];
                var areacCoordinate = element.areacCoordinate;
                dataById.push({
                  id: element.id,
                  areacCoordinate: areacCoordinate
                });
                draw(areacCoordinate, element.id);
              }
              if (rows.length > 0) markmap.setViewport(q.getPath()); //调整视野
              // draw({ areacoords: areacCoordinate });
            })
            .catch(function(err) {
              console.log(err);
            });
        }
      }
    });
    markmap.addEventListener("rightclick", rightclkFunc);
    markmap.addEventListener("mousemove", function(
      type,
      target,
      point,
      pixel,
      overlay
    ) {
      centerPoint = [type.point.lng, type.point.lat];
    });
    markmap.addEventListener("zoomend", function(type, target) {
      var zoom = markmap.getZoom();
      var point = centerPoint;
      var id = "";
      if (zoom > 12) {
        f1: for (let i = 0; i < dataById.length; i++) {
          let element = dataById[i];
          var points = getArrayAreacCoordinate(element);
          f2: for (let i = 0; i < points.length; i++) {
            var b = isInPolygon(point, points[i]);
            if (b) {
              id = element.id;
              break f1;
            }
          }
        }
      } else {
        id = "6941139967a311e8a93e1843420641b8";
      }
      if (id != "") drowMap(id);
    });
    var id = "6941139967a311e8a93e1843420641b8";
    drowMap(id);

    this.$ajax({
      method: "post",
      url: this.$uri + "mapInterface/getSessionData"
    })
      .then(function(res) {
        dataById = [];
        var state = res.data.state;
        if (state == 0) {
          sessionData = 0;
          alert("数据加载完毕！");
        } else if (state == 1) {
          sessionData = 0;
        }
        // draw({ areacoords: areacCoordinate });
      })
      .catch(function(err) {
        console.log(err);
      });
  }
};
</script>
<style scoped>
.box_card {
  padding: 5px;
}
</style>

