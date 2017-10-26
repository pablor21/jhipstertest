import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GithubClientSharedModule } from '../shared';

import { ME_ROUTE, MeComponent } from './';

@NgModule({
    imports: [
        GithubClientSharedModule,
        RouterModule.forRoot([ ME_ROUTE ], { useHash: true })
    ],
    declarations: [
        MeComponent,
    ],
    entryComponents: [
    ],
    providers: [
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GithubClientMeModule {}
