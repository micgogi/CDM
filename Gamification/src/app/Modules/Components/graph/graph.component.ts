import { Component, OnInit } from '@angular/core';
import { Chart } from 'chart.js';
import { GameService } from '../../Services/game.service';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { BreakpointObserver, BreakpointState } from '@angular/cdk/layout';


@Component({
  selector: 'app-graph',
  templateUrl: './graph.component.html',
  styleUrls: ['./graph.component.css']
})
export class GraphComponent implements OnInit {
result:any;
  title = 'day-chart';
	constituency:string;
  height;
  width;
  margin;
	time = [];
	partyA = [];
	partyB = [];

  dayWiseChart:any = [];



  constructor(private route:ActivatedRoute,private service:GameService,
    private datePipe:DatePipe,public breakpointObserver: BreakpointObserver ) { 
    this.route.params.subscribe(
      response=>this.constituency = response['constituency']
      
    );
  }

  ngOnInit() {
    this.breakpointObserver.observe(['(max-width: 700px)']).subscribe(
      (state:BreakpointState)=>{
        if(state.matches){
          this.height = '400';
          this.width = '320';
          this.margin = 'auto';
        }
        else {
          this.height = '500';
          this.width = '600';
          this.margin = 'auto';
        }
          
      }
    );
    this.service.getGraphDetails(this.constituency).subscribe(data=>{
      console.log(data);
      this.result=data;
      console.log(this.result);
      for(let i =  this.result.length -1 ; i > -1 ; i--) {
        this.time.push(this.datePipe.transform(this.result[i]['created'],'MMM d, y'));
        this.partyA.push(this.result[i]['party1Percent']);
        this.partyB.push(this.result[i]['party2Percent']);
      }
  
      this.generateDayWiseChart();
    })

    
  }
  generateDayWiseChart() {
	  this.dayWiseChart = new Chart("dayWiseChart", {
			type: 'line',
			data: {
				labels: this.time,
				datasets: [{
					label: 'Party 1',
          backgroundColor: "rgb(200, 0, 161)",
					borderColor: "rgb(200, 0, 161)",
					data: this.partyA,
					fill: false,
				}, {
					label: 'Party 2',
					fill: false,
					backgroundColor: "rgb(1, 158, 58)",
					borderColor: "rgb(1, 158, 58)",
					data: this.partyB,
				}]
			},
			options: {
      responsive: true,
      maintainAspectRatio: true,
      legend: {
        labels: {
            fontColor: "white",
        }
    },
				title: {
					display: true,
          text: 'Daywise Progress of Parties',
          fontColor: '#ffffff'
				},
				tooltips: {
					mode: 'index',
					intersect: false,
				},
				hover: {
					mode: 'nearest',
					intersect: true
				},
				scales: {
					xAxes: [{
						display: true,
					
						scaleLabel: {
							display: true,
              labelString: 'Days',
              fontColor: '#ffffff'
            },
            ticks: {
              fontColor: "white",
             
          },
           gridLines :{
             color: 'rgba(255,255,255,0.2)'
           }
					}],
					yAxes: [{
						display: true,
					
						scaleLabel: {
							display: true,
              labelString: 'Vote Percentage',
              fontColor: '#ffffff'
            },
            ticks: {
              fontColor: "white",
          },
          gridLines: {
             color: 'rgba(255,255,255,0.2)'
           }
					}]
				}
			}
    });  
  }
}
