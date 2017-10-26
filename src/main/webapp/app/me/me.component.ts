import { Component, OnInit } from '@angular/core';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { GitService, ResponseWrapper, Principal, Account } from '../shared';

@Component({
    providers: [GitService],
    selector: 'jhi-me',
    templateUrl: './me.component.html',
    styleUrls: [
        'me.css'
    ]

})
export class MeComponent implements OnInit {
    account: Account;
    modalRef: NgbModalRef;
    data: Object;

    constructor(private principal: Principal, private gitService: GitService, private eventManager: JhiEventManager) {
    }

    ngOnInit() {
        this.load();
        this.principal.identity().then((account) => {
            this.account = account;
        });
        this.registerAuthenticationSuccess();
    }

    registerAuthenticationSuccess() {
        this.eventManager.subscribe('authenticationSuccess', (message) => {
            this.principal.identity().then((account) => {
                this.account = account;
            });
        });
    }

    isAuthenticated() {
        return this.principal.isAuthenticated();
    }

    load() {
        this.gitService.query('/user').subscribe(
            (res: ResponseWrapper) => {
                this.data = res.json;
            },
            (res: ResponseWrapper) => {
                alert(res.json.message)
            }
        );
    }

}
