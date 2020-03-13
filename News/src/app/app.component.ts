import { Component,OnInit } from '@angular/core'
import { News } from './news';
import { FormGroup, FormControl } from '@angular/forms';
import { NewsService } from './news.service';
import { HttpClient } from '@angular/common/http';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
 

  ngOnInit() {

   
  }



}
