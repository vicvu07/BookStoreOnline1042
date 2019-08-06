$(document).ready(function() {

    'use strict';

    var listLabel = {} ;
    var listData = {};

    // Global Options
    Chart.defaults.global.defaultFontFamily = 'Lato';
    Chart.defaults.global.defaultFontSize = 18;
    Chart.defaults.global.defaultFontColor = '#777';

    function setDataChart(arr) {
        listLabel= [];
        listData = [];
        for(var i = 0; i < arr.length; i++) {
            listLabel.push(arr[i].label);
            listData.push(arr[i].data);

        }

    };




    function chartCountProductByCategoryTypeBar() {
        setDataChart(vm.countBookByCategory.labelDataList);



        let myChart = $('#chart-count-product-by-category-type-bar')[0].getContext('2d');


        let massPopChart = new Chart(myChart, {
            type:'bar', // bar, horizontalBar, pie, line, doughnut, radar, polarArea
            data:{
                labels:listLabel,
                datasets:[{
                    label:'Số đầu sách',
                    data: listData,
                    //backgroundColor:'green',
                    backgroundColor:[
                        'rgba(255, 99, 132, 0.6)',
                        'rgba(54, 162, 235, 0.6)',
                        'rgba(255, 206, 86, 0.6)',
                        'rgba(75, 192, 192, 0.6)',
                        'rgba(153, 102, 255, 0.6)',
                        'rgba(255, 159, 64, 0.6)',
                        'rgba(255, 99, 132, 0.6)'
                    ],
                    borderWidth:1,
                    borderColor:'#777',
                    hoverBorderWidth:3,
                    hoverBorderColor:'#000'
                }]
            },
            options:{
                /*title:{
                    display:true,
                    text:'Count product by category display bar',
                    fontSize:25
                },*/
                legend:{
                    display:false,
                    position:'right',
                    labels:{
                        fontColor:'#000'
                    }
                },
                layout:{
                    padding:{
                        left:0,
                        right:0,
                        bottom:0,
                        top:0
                    }
                },
                tooltips:{
                    enabled:true
                }
            }
        });
    }


    function chartCountProductByCategoryTypePie() {
        let myChart = $('#chart-count-product-by-category-type-pie')[0].getContext('2d');

        let massPopChart = new Chart(myChart, {
            type:'pie', // bar, horizontalBar, pie, line, doughnut, radar, polarArea
            data:{
                labels:listLabel,
                datasets:[{
                    label:'Product',
                    data: listData,
                    //backgroundColor:'green',
                    backgroundColor:[
                        'rgba(255, 99, 132, 0.6)',
                        'rgba(54, 162, 235, 0.6)',
                        'rgba(255, 206, 86, 0.6)',
                        'rgba(75, 192, 192, 0.6)',
                        'rgba(153, 102, 255, 0.6)',
                        'rgba(255, 159, 64, 0.6)',
                        'rgba(255, 99, 132, 0.6)'
                    ],
                    borderWidth:1,
                    borderColor:'#777',
                    hoverBorderWidth:3,
                    hoverBorderColor:'#000'
                }]
            },
            options:{
                title:{
                    display:true,
                    text:'Count product by category display pie',
                    fontSize:25
                },
                legend:{
                    display:true,
                    position:'right',
                    labels:{
                        fontColor:'#000'
                    }
                },
                layout:{
                    padding:{
                        left:0,
                        right:0,
                        bottom:0,
                        top:0
                    }
                },
                tooltips:{
                    enabled:true
                }
            }
        });
    }
    function chartSumProductByCategoryTypeBar() {
        setDataChart(vm.sumBookByCategory.labelDataList);

        let myChart = $('#chart-sum-product-by-category-type-bar')[0].getContext('2d');


        let massPopChart = new Chart(myChart, {
            type:'bar', // bar, horizontalBar, pie, line, doughnut, radar, polarArea
            data:{
                labels:listLabel,
                datasets:[{
                    label:'Product',
                    data: listData,
                    //backgroundColor:'green',
                    backgroundColor:[
                        'rgba(255, 99, 132, 0.6)',
                        'rgba(54, 162, 235, 0.6)',
                        'rgba(255, 206, 86, 0.6)',
                        'rgba(75, 192, 192, 0.6)',
                        'rgba(153, 102, 255, 0.6)',
                        'rgba(255, 159, 64, 0.6)',
                        'rgba(255, 99, 132, 0.6)'
                    ],
                    borderWidth:1,
                    borderColor:'#777',
                    hoverBorderWidth:3,
                    hoverBorderColor:'#000'
                }]
            },
            options:{
                title:{
                    display:true,
                    text:'sum product by category display bar',
                    fontSize:25
                },
                legend:{
                    display:false,
                    position:'right',
                    labels:{
                        fontColor:'#000'
                    }
                },
                layout:{
                    padding:{
                        left:50,
                        right:0,
                        bottom:0,
                        top:0
                    }
                },
                tooltips:{
                    enabled:true
                }
            }
        });
    }


    function chartSumProductByCategoryTypePie() {
        let myChart = $('#chart-sum-product-by-category-type-pie')[0].getContext('2d');
      /*  removeZero();*/

        let massPopChart = new Chart(myChart, {
            type:'pie', // bar, horizontalBar, pie, line, doughnut, radar, polarArea
            data:{
                labels:listLabel,
                datasets:[{
                    label:'Product',
                    data: listData,
                    //backgroundColor:'green',
                    backgroundColor:[
                        'rgba(255, 99, 132, 0.6)',
                        'rgba(54, 162, 235, 0.6)',
                        'rgba(255, 206, 86, 0.6)',
                        'rgba(75, 192, 192, 0.6)',
                        'rgba(153, 102, 255, 0.6)',
                        'rgba(255, 159, 64, 0.6)',
                        'rgba(255, 99, 132, 0.6)'
                    ],
                    borderWidth:1,
                    borderColor:'#777',
                    hoverBorderWidth:3,
                    hoverBorderColor:'#000'
                }]
            },
            options:{
               /* title:{
                    display:true,
                    text:'sum product by category display pie',
                    fontSize:25
                },*/
                legend:{
                    display:true,
                    position:'bottom',
                    labels:{
                        fontColor:'#000'
                    }
                },
                layout:{
                    padding:{
                        left:0,
                        right:0,
                        bottom:0,
                        top:0
                    }
                },
                tooltips:{
                    enabled:true
                }
            }
        });
    }

    function main() {
        chartCountProductByCategoryTypeBar();
       /* chartCountProductByCategoryTypePie();*/
      /*  chartSumProductByCategoryTypeBar();*/
        chartSumProductByCategoryTypePie();
    }

    main();

});