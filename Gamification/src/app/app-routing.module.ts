import { NgModule } from '@angular/core';
import { Routes, RouterModule ,PreloadAllModules} from '@angular/router';
import { DashboardComponent } from './Modules/Components/dashboard/dashboard.component';
import { DetailsComponent } from './Modules/Components/details/details.component';
import { HomeComponent } from './Modules/Components/home/home.component';
import { MapComponent } from './Modules/Components/map/map.component';
import { GraphComponent } from './Modules/Components/graph/graph.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';

const routes: Routes = [

    
    {
      path:'',
      component:HomeComponent
    },
    {
        path: 'dashboard',
        component: DashboardComponent,
    },
    {
        path: 'details/:constituency',
        component: DetailsComponent,
    },    
    {
        path:'map',
        component:MapComponent
    } ,
    {
        path:'graph/:constituency',
        component: GraphComponent
    },
    {
        path:'**',
        component: PageNotFoundComponent
    }
];



@NgModule({
  imports: [
      RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules }),
    ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
