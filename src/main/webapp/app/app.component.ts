import { Component } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { LANGUAGES } from "./app.constants";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  constructor(translateService: TranslateService) {
    translateService.addLangs(LANGUAGES);
    translateService.setDefaultLang(LANGUAGES[0]);
  }
}
