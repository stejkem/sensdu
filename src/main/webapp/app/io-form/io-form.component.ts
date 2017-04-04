import { Component, OnInit } from '@angular/core';
import { LANGUAGES } from "../app.constants";
import { Http, Response, Headers, RequestOptions } from "@angular/http";

@Component({
  selector: 'app-io-form',
  templateUrl: './io-form.component.html',
  styleUrls: ['./io-form.component.css']
})
export class IoFormComponent implements OnInit {
  private languages: string[];

  private loading: boolean;
  private data: Object;

  constructor(private http: Http) {
    this.languages = LANGUAGES;
  }

  onSubmit(form: any): void {
    console.log('The form you\'ve submitted:', form);
    this.loading = true;

    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });

    this.http.post("http://localhost:8080/api/translate", JSON.stringify(form), options)
      .subscribe((response: Response) => {
        this.data = response.json();
        this.loading = false;
      });
  }

  ngOnInit() {
  }
}
