import { Component, OnInit} from '@angular/core';
import {  BsModalRef } from 'ngx-bootstrap/modal';

import { News } from '../news';
import { HttpClient } from '@angular/common/http';
import { NewsService } from '../news.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Councilor } from '../councilor';
import { CouncilorService } from '../councilor.service';
import { UserService } from '../user.service';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  invalidForm: boolean = false;
  invalidForm2: boolean = false;
  userName: string;
  password: string;
  modalRef: BsModalRef;
  show: boolean;
  message: string;
  news: News;
  updateMessage: string;
  updateCouncillorMessage: string;
  newsList: Array<News>;
  showViewform: boolean;
  showViewNav: boolean;
  editCouncilor:boolean;
  newsForm: FormGroup;
  councilorForm: FormGroup;
  councilor: Councilor;
  editNews: boolean;
  showUpdateMessage: boolean;
  showContituencyTable:boolean;
  selectedConstituency:string;
  selectedCouncilorConstituency:string;
  councillorList: Array<Councilor>;
  constituencies: any[];
  cardView:boolean;
  newsedit=new News(null,null,null,null,null);
  councilorEdit=new Councilor(null,null,null,null);
  // @ViewChild('staticModal') public staticModal: ModalDirective;
 


  constructor(private http: HttpClient, private newsService: NewsService, private router: Router, private councilorService: CouncilorService, private userService: UserService) {


    this.newsForm = new FormGroup({
      title: new FormControl('', [Validators.required]),
      type: new FormControl('', [Validators.required]),
      description: new FormControl('', [Validators.required]),
      url: new FormControl('', [Validators.required]),
      constituency: new FormControl('', [Validators.required])
    });


    this.councilorForm = new FormGroup({
      name: new FormControl('', [Validators.required]),
      url: new FormControl('', [Validators.required]),
      constituency: new FormControl('', [Validators.required]),
      party: new FormControl('', [Validators.required])
    });

    this.showViewform = true;
    this.showViewNav = true;
    this.newsService.getConstituencies().subscribe(response => {
      this.constituencies = response;
      this.constituencies.sort();
    });
  }

  ngOnInit() {
    console.log('home')
  }

  ngAfterViewInit() {
    this.showViewform = true;
  }
  // open(){
  //   this.staticModal.show();
  // }
  callFunction(Username: string, Password: string) {
    let user = {
      userName: Username,
      password: Password
    }
    this.userService.login(user).subscribe(response => {
      // this.staticModal.hide();
      this.showViewform = false;
      this.showViewNav = false;

    }, (error: Response) => {
      if (error.status == 401) {
        this.message = "wrong credentials";
        this.show = true;
      } else {
        this.message = "server down";
        this.show = true;
      }
    });
  }

  onSubmit() {
    if (this.newsForm.invalid) {
      this.invalidForm = true;
      setTimeout(() => {
        this.invalidForm = false;
      },
        800)
      return;
    }
    this.news = new News(this.newsForm.get('title').value, this.newsForm.get('description').value, this.newsForm.get('type').value, this.newsForm.get('url').value, this.newsForm.get('constituency').value)
    this.newsService.createTheNews(this.news).subscribe(data => {
      alert(data);
      this.newsForm.reset();


    }, (error: Response) => {
      
       alert( "All Informations Should Be Proper");

     
      }
    );

  }


  onForm2Submit() {
    if (this.councilorForm.invalid) {
      this.invalidForm2 = true;
      setTimeout(() => {
        this.invalidForm2 = false;
      },
        800)
      return;
    }
    this.councilor = new Councilor(this.councilorForm.get('name').value, this.councilorForm.get('url').value, this.councilorForm.get('constituency').value, this.councilorForm.get('party').value);
    this.councilorService.addCouncilor(this.councilor).subscribe(response => {
      alert('Data saved successfully');
    }, (error: Response) => {
      
      alert( "All Informations Should Be Proper");

    
     }
   );

    this.councilorForm.reset();
  }

  logout() {
  this.showViewNav=true;
  this.showViewform = true;
    this.editNews = false;
    this.cardView=false;
    this.editCouncilor = false;
    this.showContituencyTable=false;
    // this.staticModal.show();
    


    
  }

  newsEdit() {
    this.showViewform = true;
    this.editNews = true;
    this.cardView=false;
    this.editCouncilor = false;

    this.newsService.getConstituencies().subscribe(response => {
      this.constituencies = response;
      this.constituencies.sort();
    });

  }

  councillorEdit(){
    this.showViewform = true;
    this.editCouncilor = true;
    this.editNews=false;
    this.showContituencyTable=false;

    this.newsService.getConstituencies().subscribe(response => {
      this.constituencies = response;
      this.constituencies.sort();
    });


  }

  home(){
    this.showViewform = false;
    this.showViewNav = false;
    this.editCouncilor = false;
    this.showContituencyTable=false;
    this.editNews=false;
    this.cardView=false;
  }

  newsTable() {
console.log(this.selectedConstituency);

    this.showViewform = true;
    this.showContituencyTable = true;
    this.newsService.getConstituencyNews(this.selectedConstituency).subscribe(response => {
      console.log(response);
  this.newsList=response;

    })

  }

  retriveNews(id){
    console.log(id);
    this.newsService.getNewsById(id).subscribe(data=>{
      this.newsedit=data;
    });

   


  }

  UpdateNews(news:News){
  this.newsService.updateNews(news).subscribe(data=>{

    console.log(data);
    this.updateMessage=data;
    this.showUpdateMessage=true;
    setTimeout(() => {
      this.showUpdateMessage = false;
    },
      800);
    this.newsService.getConstituencyNews(this.selectedConstituency).subscribe(response => {
      console.log(response);
  this.newsList=response;

    });
    
  }, (error: Response) => {
    if (error.status == 404) {
      this.updateMessage = "No Records Found";
      this.showUpdateMessage=true;
    setTimeout(() => {
      this.showUpdateMessage = false;
    },
      800);
    } else {
      this.updateMessage = "server down";
      this.showUpdateMessage=true;
    setTimeout(() => {
      this.showUpdateMessage = false;
    },
      800);
    }
  });


    
    
    
  }

  deleteNews (news:News){

    const index = this.newsList.indexOf(news);
    this.newsList.splice(index,1);

    this.newsService.deleteNews(news.id).subscribe(data=>{
      console.log(data);
      
    })
  }

  councillorRetrive(){
    this.cardView=true;
  this.councilorService.getCouncillor(this.selectedCouncilorConstituency).subscribe(data=>{
  this.councillorList=data;
  
});
    
  }

  retriveCouncilor(councillor){

this.councilorEdit=councillor;


 this.councilorEdit.party=councillor.councilorIdentity.party;
 this.councilorEdit.constituency=councillor.councilorIdentity.constituency;


  }

  updateCouncilor(){
    this.councilorService.addCouncilor(this.councilorEdit).subscribe(data=>{
      this.councillorRetrive();
      this.updateCouncillorMessage="Updated";
    this.showUpdateMessage=true;
    setTimeout(() => {
      this.showUpdateMessage = false;
    },
      800);
      

    }
)};}

