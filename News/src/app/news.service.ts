import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { News } from './news';


@Injectable({
  providedIn: 'root'
})
export class NewsService {
    endPoint:string;
    endPointElectionService:string;
  constructor(private http:HttpClient) {
    //this.endPoint ='http://10.230.179.10:8090/api/v1/newsservice';
    this.endPoint='http://10.142.198.11:80/AdminService/api/v1/newsservice';

    //this.endPointElectionService='http://10.230.179.10:8082/api/v1/digitalservice';
    this.endPointElectionService='http://10.142.198.11:80/ElectionService/api/v1/digitalservice';
   }

   createTheNews(news:News){
    return this.http.post(this.endPoint,news,{responseType:'text'});
}

    getConstituencies(){
      let url=`${this.endPointElectionService}/constituency`;
      return this.http.get<Array<any>>(url); 
    }

    getConstituencyNews(data){
      let url=`${this.endPoint}/${data}`;
      return this.http.get<Array<any>>(url);
    }

    getNewsById(id){
      let url=`${this.endPoint}/news/${id}`;
      return this.http.get<News>(url);
    }

    updateNews(news:News){
      let url=`${this.endPoint}`
     return this.http.put(url, news,{responseType:'text'});
    }

    deleteNews (id){
      let url=`${this.endPoint}/newsdelete/${id}`;
      return this.http.delete(url,{responseType:'text'});
    }
}
