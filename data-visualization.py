
data = [   
    # 1 partition
# 1 consumer
{
    "avgThroughputMbps": 2.4789922377642463E-4,
    "maxLatencyMs": 299
},
# 2 consumer
{
    "avgThroughputMbps": 2.504233324562095E-4,
    "maxLatencyMs": 307
},
# 5 consumer
{
    "avgThroughputMbps": 2.574074888083548E-4,
    "maxLatencyMs": 297
},
# 10 consumer
{
    "avgThroughputMbps": 2.486620630536761E-4,
    "maxLatencyMs": 305
},

    # 2 partitions
# 2 consumer
{
    "avgThroughputMbps": 4.622836312847937E-4,
    "maxLatencyMs": 291
},
# 5 consumer
{
    "avgThroughputMbps": 4.701061946589772E-4,
    "maxLatencyMs": 295
},
# 10 consumer
{
    "avgThroughputMbps": 4.711999483635089E-4,
    "maxLatencyMs": 289
},

# 10 partitions, 10 consumers
{
    "avgThroughputMbps": 0.0019595891060913808,
    "maxLatencyMs": 245
},

# 2 producers, 10 partitions, 10 consumers
{
    "avgThroughputMbps": 0.0021131759466126907,
    "maxLatencyMs": 275
}
]

throughputs = list(map(lambda x: x["avgThroughputMbps"], data))
maxLatency = list(map(lambda x: x["maxLatencyMs"], data))

import matplotlib.pyplot as plt

plt.figure(figsize=(10, 7))
plt.plot(list(range(1, len(data) + 1)), throughputs)
plt.xlabel('# of experiment')
plt.ylabel('Throughput (Mbps)')
plt.title('Throughput vs Configuration')
plt.show()

plt.figure(figsize=(10, 7))
plt.plot(list(range(1, len(data) + 1)), maxLatency)
plt.xlabel('# of experiment')
plt.ylabel('Max latency (ms)')
plt.title('Max latency vs Configuration')
plt.show()