import { Component, OnInit } from '@angular/core';
import { GameService } from '../../Services/game.service';
import { ActivatedRoute } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import { DomSanitizer } from '@angular/platform-browser';


@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.css']
})
export class DetailsComponent implements OnInit {
  constituency: string;
  party1: any;
  party2: any;
  totalVotes: string;
  status1: boolean;
  status2: boolean;
  chart: any;
  n: number;
  councilor = { p1: '', p2: '' };
  counter = this.n - 1;
  time = 10000;
  progress: any;
  myNews:any;
  news: any;
  dot: any;
  public searchString: string;
  members:any[];
  constructor(private service: GameService, private route: ActivatedRoute,
    private spinner: NgxSpinnerService, public sanitizer: DomSanitizer) {
      this.myNews = {};

    this.route.params.subscribe(
      response => this.constituency = response['constituency']

    );
    this.status1 = true;
    this.status2 = false;
  }

  ngOnInit() {

    this.spinner.show();

    setTimeout(() => {
      this.spinner.hide();
    }, 5000);
    this.service.getPartyMemebers(this.constituency).subscribe(
      response => {
       // console.log(response);
        this.members = response['Party 1']['members']
        this.members.concat(response['Party 2']['members'])
        console.log(this.members);
        this.party1 = response['Party 1'];
        this.party2 = response['Party 2'];
        this.totalVotes = response['totalVotes'];
      }
    );
    this.service.getCampaignNews(this.constituency).subscribe(
      (response:any[]) => {
        
        this.news = response;
        
        if(response.length==0){
         this.service.getRandomNews().subscribe(response=>{
           this.news=response;

           console.log(response);
         })
        }
       
      }
    );
    this.service.getCouncilor(this.constituency).subscribe((response: any[]) => {
      console.log(response);
      for (let i of response) {
        if (i.party === 'Party 1')
          this.councilor.p1 = i;
        else if (i.party === 'Party 2')
          this.councilor.p2 = i;
      }
    });
  }
  buttonStatus1() {
    this.status1 = true;
    this.status2 = false;
  }
  buttonStatus2() {
    this.status2 = true;
    this.status1 = false;
  }
  showMe(news) {
    this.myNews = news;
  }
  getUrl(url) {
    return this.sanitizer.bypassSecurityTrustResourceUrl(url);
  }
  close(){
    console.log("closed video")
   
   
  
  }
}
