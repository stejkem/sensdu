import { Component, OnInit } from '@angular/core';
import { LANGUAGES } from "../app.constants";

@Component({
  selector: 'app-io-form',
  templateUrl: './io-form.component.html',
  styleUrls: ['./io-form.component.css']
})
export class IoFormComponent implements OnInit {
  private languages: string[];

  constructor() {
    this.languages = LANGUAGES;
  }

  ngOnInit() {
  }

}
