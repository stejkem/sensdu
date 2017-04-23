import {Component, OnInit} from '@angular/core';
import {LANGUAGES} from "../app.constants";
import {Http, Headers, RequestOptions} from "@angular/http";
import {TranslationResponse} from "../data/TranslationResponse";

@Component({
    selector: 'app-io-form',
    templateUrl: './io-form.component.html',
    styleUrls: ['./io-form.component.css']
})
export class IoFormComponent implements OnInit {
    private languages: string[];

    private loading: boolean;
    private data: TranslationResponse;

    constructor(private http: Http) {
        this.languages = LANGUAGES;
        this.data = new TranslationResponse();
    }

    onSubmit(form: any): void {
        this.loading = true;

        let headers = new Headers({'Content-Type': 'application/json'});
        let options = new RequestOptions({headers: headers});

        this.http.post("/api/translate", JSON.stringify(form), options)
            .map(response => <TranslationResponse>response.json())
            .subscribe(data => {
                    this.data = data;
                    this.loading = false;
                },
                error => {
                    this.data = new TranslationResponse();
                    this.data.translation = "no-translation-available";
                });
    }

    ngOnInit() {
    }
}
