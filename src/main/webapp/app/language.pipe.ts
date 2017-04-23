import {Pipe, PipeTransform} from '@angular/core';

@Pipe({name: 'findLanguageFromKey'})
export class FindLanguageFromKeyPipe implements PipeTransform {
    private languages: any = {
        'de': 'Deutsch',
        'en': 'English',
        'ru': 'Русский',
        'uk': 'Українська'
    };

    transform(languageKey: string): string {
        return this.languages[languageKey];
    }
}
