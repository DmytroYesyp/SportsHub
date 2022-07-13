import {Component, NgModule, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";


interface text {
  value: string;
  viewValue: string;
}

@Component({
  selector: 'app-admin-page',
  templateUrl: './admin-page.component.html',
  styleUrls: ['./admin-page.component.css'],
})

export class AdminPageComponent implements OnInit {
  selectedValue: string;
  selectedValue2: string;
  selectedValue3: string;
  selectedValue4: string;
  dateLimit: any;
  dateLimitList: any;

  values: text[] = [
    {value: 'text-1', viewValue: 'text-1'},
    {value: 'text-2', viewValue: 'text-2'},
    {value: 'text-3', viewValue: 'text-3'},
  ];

  constructor(private http : HttpClient) { }

  ngOnInit(): void {
    this.http.get('http://localhost:8080/datelimits')
      .subscribe((Response) => {
        this.dateLimitList = <Array<any>>Response
        if (this.dateLimitList[0]['datelim']>this.dateLimitList[1]['datelim']) {
          this.dateLimit = this.dateLimitList[0]['datelim']
        }
        else {
          this.dateLimit = this.dateLimitList[1]['datelim']
        }
      });
  }

  changeDateLimit(a){
    for(let i = 0; i<this.dateLimitList.length; i++) {
      if (this.dateLimitList[i]['datelim'] != 0) {
        this.http.put('http://localhost:8080/datelimits/' + (i+1), {
          "datelim": a,
        })
          .subscribe(() => {
          });
      }
    }
    window.location.reload()
  }

  hide(id) {
    this.http.put('http://localhost:8080/datelimits/' + id, {"datelim": 0})
      .subscribe(()=> {
        window.location.reload()
      });
  }

  show(id) {
    this.http.put('http://localhost:8080/datelimits/' + id, {"datelim": this.dateLimitList[2]['datelim']})
      .subscribe(()=> {
        window.location.reload()
      });
  }
}
