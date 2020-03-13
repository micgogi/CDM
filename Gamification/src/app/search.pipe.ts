import { Pipe, PipeTransform, Injectable } from '@angular/core';

// @Pipe({
//     name: 'filter',
// })
// @Injectable()
// export class FilterPipe implements PipeTransform {
//     transform(items: any[], field: string, value: string): any[] {
//         if (!items) {
//             return [];
//         }
//         if (!field || !value) {
//             return items;
//         }

//         return items.filter(singleItem =>
//             singleItem[field].toLowerCase().includes(value.toLowerCase())
//         );
//     }
// }


@Pipe({
    name: "arrayFilter"
})

export class BuilderFilterPipe implements PipeTransform {

    transform(value:any[],searchString:string ){

       if(!searchString){
        
         return value  
       }

       return value.filter(it=>{   
           const builderId = it.associateId.toString().includes(searchString) 
           const groupName = it.associateName.toLowerCase().includes(searchString.toLowerCase())
           return (builderId + groupName  );      
       }) 
    }
}