#!/usr/bin/env python
# -*- coding: utf-8 -*-


from pprint import pprint
from feast import FeatureStore
store = FeatureStore(repo_path="../../gpc_test")
feature_vector = store.get_online_features(
    features=[
      "driver_hourly_stats:conv_rate",
      "driver_hourly_stats:acc_rate",
      "driver_hourly_stats:avg_daily_trips",
    ],
    entity_rows=[
      {"driver_id": 1007},
      {"driver_id": 1008},
    ],
).to_dict()
pprint(feature_vector)