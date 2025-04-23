import { TestBed } from '@angular/core/testing';

import { Module1Service } from './module1.service';

describe('Module1Service', () => {
  let service: Module1Service;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Module1Service);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
