import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {Http, HttpModule} from '@angular/http';
import {MaterialModule} from '@angular/material';
import {FlexLayoutModule} from '@angular/flex-layout';
import {TranslateLoader, TranslateModule} from '@ngx-translate/core';
import {TranslateHttpLoader} from '@ngx-translate/http-loader';

import {FindLanguageFromKeyPipe} from './language.pipe';

import {AppComponent} from './app.component';
import {NavbarComponent} from './navbar/navbar.component';
import {IoFormComponent} from './io-form/io-form.component';
import {AboutComponent} from './about/about.component';
import {RouterModule, Routes} from "@angular/router";

export function HttpLoaderFactory(http: Http) {
    return new TranslateHttpLoader(http, './i18n/', '.json');
}

const routes: Routes = [
    {path: '', component: IoFormComponent},
    {path: 'about', component: AboutComponent},
];

@NgModule({
    declarations: [
        AppComponent,
        NavbarComponent,
        FindLanguageFromKeyPipe,
        IoFormComponent,
        AboutComponent
    ],
    imports: [
        BrowserModule,
        FormsModule,
        ReactiveFormsModule,
        HttpModule,
        MaterialModule,
        FlexLayoutModule,
        RouterModule.forRoot(routes),
        TranslateModule.forRoot({
            loader: {
                provide: TranslateLoader,
                useFactory: HttpLoaderFactory,
                deps: [Http]
            }
        })
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule {
}
