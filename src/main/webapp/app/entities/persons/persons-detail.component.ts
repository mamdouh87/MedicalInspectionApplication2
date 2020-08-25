import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPersons } from 'app/shared/model/persons.model';

@Component({
  selector: 'jhi-persons-detail',
  templateUrl: './persons-detail.component.html',
})
export class PersonsDetailComponent implements OnInit {
  persons: IPersons | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ persons }) => (this.persons = persons));
  }

  previousState(): void {
    window.history.back();
  }
}
