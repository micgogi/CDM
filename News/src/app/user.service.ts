import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { User } from './user';


@Injectable({
  providedIn: 'root'
})
export class UserService {

    endPoint:string;

  constructor(private http:HttpClient) {
    //local
    this.endPoint ='http://10.230.179.10:8090/api/v1/userservice';
    //server
     //this.endPoint='http://10.142.198.11:80/AdminService/api/v1/userservice';
   }

   login(user:User){
    const url=`${this.endPoint}/login`;
    return this.http.post(url,user,{responseType:'text'});
   }
}