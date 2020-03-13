import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Councilor } from './councilor';

@Injectable({
  providedIn: 'root'
})
export class CouncilorService {
  endPoint:string;
  constructor(private http:HttpClient) {
     //this.endPoint ='http://10.230.179.10:8090/api/v1/councilorservice';

    this.endPoint='http://10.142.198.11:80/AdminService/api/v1/councilorservice';

   }

  addCouncilor(councilor:Councilor){
    let councilorJson = {
      name:councilor.name,
      url:councilor.url,
      councilorIdentity:{
        party:councilor.party,
        constituency:councilor.constituency
      }
      
    }
    console.log(councilorJson);
    return this.http.post(this.endPoint,councilorJson,{responseType:'text'});
  }

  getCouncillor( constituency){
    console.log("in service");
    
let url= `${this.endPoint}/${constituency}`;
    return this.http.get<Array<Councilor>>(url);
  }
}
