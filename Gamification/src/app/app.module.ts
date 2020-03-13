import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule} from '@angular/common/http';
import { TooltipModule } from 'ng2-tooltip-directive';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { LayoutModule } from '@angular/cdk/layout';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DetailsComponent } from './Modules/Components/details/details.component';
import { DashboardComponent } from './Modules/Components/dashboard/dashboard.component';
import { ChartsModule } from 'ng2-charts';
import { GaugeModule } from 'angular-gauge';
import { AngularFullpageModule} from '@fullpage/angular-fullpage';
import { ParticlesModule } from 'angular-particle';
import { HomeComponent } from './Modules/Components/home/home.component';
import { NgxSpinnerModule } from 'ngx-spinner';
//import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { MapComponent } from './Modules/Components/map/map.component';
import { GraphComponent } from './Modules/Components/graph/graph.component';
import { DatePipe } from '@angular/common';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';

import { FormsModule } from '@angular/forms';
import { BuilderFilterPipe } from './search.pipe';



@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    DetailsComponent,
    HomeComponent,
    MapComponent,
    GraphComponent,
    PageNotFoundComponent,
    BuilderFilterPipe
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    LayoutModule,
    FormsModule,
    HttpClientModule,
    TooltipModule,
    ChartsModule,
    AngularFullpageModule,
    GaugeModule.forRoot(),
    ParticlesModule,
    NgxSpinnerModule,
  //  MDBBootstrapModule.forRoot()
    ],
  providers: [DatePipe],
  bootstrap: [AppComponent],
  exports: [
    // ...
    BuilderFilterPipe,
],

})
export class AppModule { }
