import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAbdominalInseption } from 'app/shared/model/abdominal-inseption.model';

@Component({
  selector: 'jhi-abdominal-inseption-detail',
  templateUrl: './abdominal-inseption-detail.component.html',
})
export class AbdominalInseptionDetailComponent implements OnInit {
  abdominalInseption: IAbdominalInseption | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ abdominalInseption }) => (this.abdominalInseption = abdominalInseption));
  }

  previousState(): void {
    window.history.back();
  }
}
