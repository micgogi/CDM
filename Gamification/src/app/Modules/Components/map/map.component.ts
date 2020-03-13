import { Component, OnInit } from '@angular/core';
import { GameService } from '../../Services/game.service';
import { NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements OnInit {
  result: any;
  cons = { Party1: 0, Party2: 0, Constituency: '' };
  name: string;
  center = { x: 0, y: 0 };
  htmlCenter = { x: 0, y: 0 };
  svgVB = { x: 0, y: 0, w: 1737.91, h: 695.76 };
  color;
  hide:boolean;

  constructor(private service: GameService,private spinner:NgxSpinnerService) {
   
    this.hide=false;
     this.service.getMap().subscribe(response => {
      this.result = response;
    });
  }

  ngOnInit() {
    this.spinner.show();
    setTimeout(() => {
      /** spinner ends after 5 seconds */
      this.spinner.hide();
      this.hide=true;
  }, 1000);

  }

  showBars(event, data) {
    console.log(event);
    this.name = data.Constituency;
    this.cons.Party1 = data.Party1;
    this.cons.Party2 = data.Party2;
    let box = event.target.getBBox();
    this.center.x = box.x + box.width / 2;
    this.center.y = box.y + box.height / 2;
    var svg = document.getElementById('svg');

    let SVGClientRect: any = svg.getBoundingClientRect();

    this.htmlCenter.x = this.map(
      this.center.x,
      this.svgVB.x,
      this.svgVB.w,
      SVGClientRect.x,
      SVGClientRect.x + SVGClientRect.width
    );

    this.htmlCenter.y = this.map(
      this.center.y,
      this.svgVB.y,
      this.svgVB.h,
      SVGClientRect.y,
      SVGClientRect.y + SVGClientRect.height
    );

    let offset = window.screen.width - event.pageX;
    let div = document.getElementById('bars');
    // div.style.left = this.htmlCenter.x - div.clientWidth / 2 + "px";
    // div.style.top = this.htmlCenter.y - div.clientHeight + "px";
    if (offset < 150 && window.screen.width > 1200)
      div.style.left = event.pageX - 120 + 'px';
    else if (offset < 90)
      div.style.left = event.pageX - 50 + 'px';
    else
      div.style.left = event.pageX + 5 + 'px';
    div.style.top = event.pageY - 25 + 'px';
    div.style.visibility = 'visible';

    let party1 = document.getElementById('party1');
    let party2 = document.getElementById('party2');

    party1.style.height = data['Party1'] + 'px';
    party2.style.height = data['Party2'] + 'px';


  }

  hideBars() {
    let div = document.getElementById('bars');
    let party1 = document.getElementById('party1');
    let party2 = document.getElementById('party2');
    div.style.visibility = 'hidden';
    party1.style.height = '0px';
    party2.style.height = '0px';
  }

  map(n, a, b, _a, _b) {
    let d = b - a;
    let _d = _b - _a;
    let u = _d / d;
    return _a + n * u;
  }
}
