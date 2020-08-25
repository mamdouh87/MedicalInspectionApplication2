import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITrafficUnits } from 'app/shared/model/traffic-units.model';

@Component({
  selector: 'jhi-traffic-units-detail',
  templateUrl: './traffic-units-detail.component.html',
})
export class TrafficUnitsDetailComponent implements OnInit {
  trafficUnits: ITrafficUnits | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ trafficUnits }) => (this.trafficUnits = trafficUnits));
  }

  previousState(): void {
    window.history.back();
  }
}
