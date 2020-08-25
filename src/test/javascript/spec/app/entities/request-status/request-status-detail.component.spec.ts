import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MedicalInspectionApplicationTestModule } from '../../../test.module';
import { RequestStatusDetailComponent } from 'app/entities/request-status/request-status-detail.component';
import { RequestStatus } from 'app/shared/model/request-status.model';

describe('Component Tests', () => {
  describe('RequestStatus Management Detail Component', () => {
    let comp: RequestStatusDetailComponent;
    let fixture: ComponentFixture<RequestStatusDetailComponent>;
    const route = ({ data: of({ requestStatus: new RequestStatus(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MedicalInspectionApplicationTestModule],
        declarations: [RequestStatusDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(RequestStatusDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(RequestStatusDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load requestStatus on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.requestStatus).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
