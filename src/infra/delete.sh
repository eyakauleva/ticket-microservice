#! /bin/bash

kubectl delete deployment.apps/tickets-deploy
kubectl delete service/tickets-service
kubectl delete configmap tickets-configmap

kubectl delete statefulset.apps/pg-stateful-tickets
kubectl delete service/pg-service-tickets
kubectl delete secret pg-secrets-tickets