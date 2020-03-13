import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class GameService {
  serverEndPoint: string;
  newsEndPoint:string;
  councilorEndPoint:string;
  constituencyId: string;
  randomNewsEndPoint:string;
  constructor(private http: HttpClient) {
   //local
    // this.serverEndPoint = 'http://10.230.179.10:8082/api/v1/digitalservice';  
    // this.newsEndPoint = 'http://10.230.179.10:8090/api/v1/newsservice';
    // this.councilorEndPoint = 'http://10.230.179.10:8090/api/v1/councilorservice';
    
    //server
    this.serverEndPoint = 'http://52.66.140.154:8082/api/v1/digitalservice';  
    //this.newsEndPoint = 'http://10.142.198.11:80/AdminService/api/v1/newsservice';
    //this.councilorEndPoint = 'http://10.142.198.11:80/AdminService/api/v1/councilorservice';
  }



  getConstiuencies() {
    const url = `${this.serverEndPoint}/constituency`;
    return this.http.get(url);
  }

  getGraphDetails(constituency) {
    const url = `${this.serverEndPoint}/daily/${constituency}`;
    return this.http.get(url);
  }
  getWinner() {
    const url = `${this.serverEndPoint}/winners`;
    return this.http.get<Array<any>>(url);
  }
  getConstituencyStatus(constituency: string) {
    const url = `${this.serverEndPoint}/party/${constituency}`;
    return this.http.get(url);
  }
  getPartyMemebers(constituency) {
    const url = `${this.serverEndPoint}/constituency/${constituency}`;
    return this.http.get(url);
  }

  getMap() {
    const url = `${this.serverEndPoint}/map`;
    return this.http.get(url);
  }

  getCampaignNews(constituency){
    const url = `${this.newsEndPoint}/${constituency}`;
    return this.http.get(url);
  }
  getCouncilor(constituency){
    const url = `${this.councilorEndPoint}/${constituency}`;
    return this.http.get(url);
  }
  getRandomNews(){
    const url = `${this.newsEndPoint}`;
    return this.http.get(url);
  }

  getkyc(id){
    const url =`${this.serverEndPoint}/participant/${id}`
    return this.http.get(url);

  }
}
