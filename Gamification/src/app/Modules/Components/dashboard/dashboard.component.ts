import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { GameService } from '../../Services/game.service';
import { NgxSpinnerService } from 'ngx-spinner';


import * as $ from 'jquery';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  date:any;
  top;
  left;
  hide:boolean;
  seatColor:String;
  Constituency = Array<any>();
  result = {};
  changeText:boolean;
  party1Votes:string;
  totalVotes:string;
  party2Votes:string;
  selectedConstituency:string;
  id:number;
  kyc:any;
  textValue:number;
  showModal:boolean;
  errorMsg:string;
  searchString:string;

  constructor(private router:Router, private service:GameService,private spinner:NgxSpinnerService) {

    this.hide=false;
    this.date = new Date();
    this.showModal = false;
    this.kyc={};
    }

  ngOnInit() {
    this.spinner.show();
    setTimeout(() => {
      /** spinner ends after 5 seconds */
      this.spinner.hide();
      this.hide=true;
  }, 1000);
  

  //   this.service.getConstiuencies().subscribe((data:Array<any>)=>{
  //  //   console.log('Constituencies-'+data);
  //     this.Constituency=data;
  //   });

    this.service.getWinner().subscribe(data=>{
      console.log(data);
      let i=0;
      for(var key in data){
        console.log(key,data[key]);
        this.Constituency[i++]=data[key]['constituency'];
      }
      this.result=data;
    });
    
    
    $(document).ready(()=>{
      $.fn.semiCircle = function(cx,cy,radius,radiusY,startDegrees,endDegrees){
        if (radiusY===undefined)      radiusY      = radius;
        if (startDegrees===undefined) startDegrees = 0;
        if (endDegrees===undefined)   endDegrees   = 180;
        var startRadians = startDegrees*Math.PI/180,
            endRadians   = endDegrees*Math.PI/180,
            stepRadians  = (endRadians-startRadians)/(this.length-1);
        return this.each(function(i){
          var a = i*stepRadians+startRadians,
              x = Math.cos(a)*radius  + cx,
              y = Math.sin(a)*radiusY + cy;
          $(this).css({position:'absolute', left:x+'px', top:y+'px'});
        });
      };
      
      $('.test').semiCircle(700,250,400,180,180,360); 
      $('.test1').semiCircle(700,300,350,160,180,360);
      $('.test2').semiCircle(700,350,300,140,180,360); 
      $('.test3').semiCircle(700,400,250,120,180,360);
      $('.test4').semiCircle(700,450,200,100,180,360); 
      
      
      });


    

  }

  onClick(cId){
    this.service.constituencyId=cId;
    console.log(cId);
    this.router.navigate(['gamification/details']);
  }
  show(c,cons){
    this.selectedConstituency = cons;
    this.service.getConstituencyStatus(cons).subscribe(
      response=>{
        this.totalVotes = response['TotalVotes'];
        this.party1Votes = response['Party 1']['votes'];
        this.party2Votes = response['Party 2']['votes'];
      }
      
    );
    console.log(c);
    
    this.left = c['pageX']-90;
    this.top = c['pageY']-80;
    console.log(c['pageX']);
    console.log(c['pageY']);
    this.changeText = true;
  }
  showDetails(cons){
    console.log(cons);
    this.service.constituencyId = cons;
    this.router.navigate(['details']);
    
  }
  getDetail(id:number){
    console.log(id);
    this.id=id;
    this.service.getkyc(id).subscribe(data=>
      {
          console.log(data);
          this.showModal=true;
          this.kyc=data;
      },(error:Response)=>{
        this.errorMsg="Please enter a valid Id number"
      });
  }
  close(){
  this.kyc=null;
   this.textValue=null;
   this.showModal = false;
  }
}
