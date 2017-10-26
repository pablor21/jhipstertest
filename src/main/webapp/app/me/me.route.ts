import { Route } from '@angular/router';

import { MeComponent } from './';

export const ME_ROUTE: Route = {
    path: 'me',
    component: MeComponent,
    data: {
        authorities: ['ROLE_ADMIN'],
        pageTitle: 'My Settings'
    }
};
