import { typeWithParameters } from '@angular/compiler/src/render3/util';

export class News {
    id:number;
    title:string
    description:string
    type:string
    url:string
    constituency:string;

    constructor(title:string,description:string,type:string,url:string,constituency:string){
        this.title=title;
        this.description=description;
        this.type=type;
        this.url=url;
        this.constituency=constituency;
    }
}