import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MedicalInspectionApplicationTestModule } from '../../../test.module';
import { TrafficUnitsDetailComponent } from 'app/entities/traffic-units/traffic-units-detail.component';
import { TrafficUnits } from 'app/shared/model/traffic-units.model';

describe('Component Tests', () => {
  describe('TrafficUnits Management Detail Component', () => {
    let comp: TrafficUnitsDetailComponent;
    let fixture: ComponentFixture<TrafficUnitsDetailComponent>;
    const route = ({ data: of({ trafficUnits: new TrafficUnits(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MedicalInspectionApplicationTestModule],
        declarations: [TrafficUnitsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TrafficUnitsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TrafficUnitsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load trafficUnits on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.trafficUnits).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
