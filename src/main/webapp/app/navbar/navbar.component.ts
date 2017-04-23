import {Component, OnInit} from '@angular/core';
import {LANGUAGES} from "../app.constants";
import {TranslateService} from "@ngx-translate/core";

@Component({
    selector: 'app-navbar',
    templateUrl: './navbar.component.html',
    styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
    private languages: string[];

    constructor(private translateService: TranslateService) {
        this.languages = LANGUAGES;
    }

    ngOnInit() {
    }
}
