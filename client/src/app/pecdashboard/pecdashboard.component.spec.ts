import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PecdashboardComponent } from './pecdashboard.component';

describe('PecdashboardComponent', () => {
  let component: PecdashboardComponent;
  let fixture: ComponentFixture<PecdashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PecdashboardComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PecdashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
